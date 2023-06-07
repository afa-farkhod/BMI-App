module com.example.bmiapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires poi.ooxml;
    requires poi;


    opens com.example.bmiapp to javafx.fxml;
    exports com.example.bmiapp;
}