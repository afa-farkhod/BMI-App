package com.example.bmiapp;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BMIComparisonApp extends Application {

    private TableView<BMIRecord> table;
    private TextField nameField;
    private TextField weightField;
    private TextField heightField;
    private TextField ageField;
    private XYChart.Series<String, Number> series;
    private XYChart<String, Number> chart;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BMI Comparison");

        // Create UI controls
        Label nameLabel = new Label("Name:");
        Label weightLabel = new Label("Weight (kg):");
        Label heightLabel = new Label("Height (cm):");
        Label ageLabel = new Label("Age:");
        nameField = new TextField();
        weightField = new TextField();
        heightField = new TextField();
        ageField = new TextField();
        Button addButton = new Button("Add User");
        addButton.setOnAction(e -> addUser());
        Button compareButton = new Button("Compare");
        compareButton.setOnAction(e -> compareBMI());
        Button exportButton = new Button("Export");
        exportButton.setOnAction(e -> exportToWord());
        table = new TableView<>();
        table.setEditable(true);

        // Create table columns
        TableColumn<BMIRecord, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<BMIRecord, Double> weightCol = new TableColumn<>("Weight");
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<BMIRecord, Double> heightCol = new TableColumn<>("Height");
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<BMIRecord, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(nameCol, weightCol, heightCol, ageCol);

        // Create chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
//        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart = new BarChart<>(xAxis, yAxis);
        chart.setTitle("BMI Comparison Chart");

        // Set up the layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(nameLabel, nameField, weightLabel, weightField, heightLabel, heightField, ageLabel, ageField, addButton, compareButton, exportButton, table, chart);

        series = new XYChart.Series<>();
        chart.getData().add(series);

        primaryStage.setScene(new Scene(vbox, 600, 800));
        primaryStage.show();
    }

    private void exportToWord() {
        String imagePath = "chart.png";
        saveChartAsImage(chart, imagePath);

        try(XWPFDocument document = new XWPFDocument()){
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            FileInputStream fis = new FileInputStream(imagePath);
            run.addPicture(fis, Document.PICTURE_TYPE_PNG, imagePath, Units.toEMU(500), Units.toEMU(300));
            fis.close();
            FileOutputStream fos = new FileOutputStream("chart.docx");
            document.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUser() {
        String name = nameField.getText();
        String weightStr = weightField.getText();
        String heightStr = heightField.getText();
        String ageStr = ageField.getText();

        if (!name.isEmpty() && !weightStr.isEmpty() && !heightStr.isEmpty() && !ageStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr);
            int age = Integer.parseInt(ageStr);

            BMIRecord record = new BMIRecord(name, weight, height, age);
            table.getItems().add(record);
            nameField.clear();
            weightField.clear();
            heightField.clear();
            ageField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please enter all the fields.");
            alert.showAndWait();
        }
    }

    private void compareBMI() {
        series.getData().clear();

        for (BMIRecord record : table.getItems()) {
            XYChart.Data<String, Number> data = new XYChart.Data<>(record.getName(), record.getBMI());
            series.getData().add(data);
        }
        try(XWPFDocument document = new XWPFDocument()){
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("BMI Comparison Chart");
            run.setFontSize(14);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveChartAsImage(Chart chart, String imagePath){
        WritableImage image = chart.snapshot(new SnapshotParameters(), new WritableImage(400, 300));
        File file = new File(imagePath);

        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class BMIRecord {
        private String name;
        private double weight;
        private double height;
        private int age;

        public BMIRecord(String name, double weight, double height, int age) {
            this.name = name;
            this.weight = weight;
            this.height = height;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public double getWeight() {
            return weight;
        }

        public double getHeight() {
            return height;
        }

        public int getAge() {
            return age;
        }

        public double getBMI() {
            double heightInMeter = height / 100.0;
            return weight / (heightInMeter * heightInMeter);
        }
    }
}
