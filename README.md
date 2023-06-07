# BMI-App
JavaFX application that allows users to compare the Body Mass Index (BMI) of individuals and export the results to a Word document. 

- BMI Comparison application that allows users to enter individuals' BMI information, compare the BMI values using a bar chart, and export the chart along with the data to a Word document.
- GUI Application main view:

![image](https://github.com/af4092/BMI-App/assets/24220136/4e37f51e-5f3e-4c08-8769-4d13cdceb529)

- The main class is called `BMIComparisonApp` and extends the Application class from JavaFX, making it the entry point for the application.
- The class defines instance variables for UI components, including a table view (table), text fields (nameField, weightField, heightField, ageField), chart series (series), and a bar chart (chart).
- The `start()` method is the main entry point for the JavaFX application. It sets up the UI components, including labels, text fields, buttons, a table view, and a bar chart.
- The `exportToWord()` method is called when the user clicks the `Export` button. It saves the chart as an image file, creates a Word document using Apache POI, and inserts the image into the document.
- The `addUser()` method is called when the user clicks the `Add User` button. It retrieves input from the text fields, creates a new BMIRecord object, adds it to the table view, and clears the text fields.
- The `compareBMI()` method is called when the user clicks the `Compare` button. It clears the chart series and populates it with BMI data from the table view's BMIRecord objects.
- The `saveChartAsImage()` method is a utility method that saves the JavaFX chart as an image file using the WritableImage and ImageIO classes.
- The BMIRecord class is a nested class within BMIComparisonApp. It represents a record of an individual's BMI information, including their name, weight, height, age, and calculated BMI.
- The BMIRecord class provides methods to access the record's attributes and calculate the BMI based on the weight and height.
- Demo test run result:

![image](https://github.com/af4092/BMI-App/assets/24220136/2a535aef-0581-4ee9-aaf5-0fd6bb9142e6)

