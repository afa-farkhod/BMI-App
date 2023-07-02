# BMI-App
JavaFX application that allows users to compare the Body Mass Index (BMI) of individuals and export the results to a Word document. 

- [Body Mass Index](https://en.wikipedia.org/wiki/Body_mass_index) - is a value derived from the mass (weight) and height of a person. The BMI is defined as the body mass divided by the square of the body height, and is expressed in units of kg/m2, resulting from mass in kilograms and height in metres.
- The BMI is a convenient rule of thumb used to broadly categorize a person as underweight, normal weight, overweight, or obese based on tissue mass (muscle, fat, and bone) and height. Major adult BMI classifications are underweight (under 18.5 kg/m2), normal weight (18.5 to 24.9), overweight (25 to 29.9), and obese (30 or more). When used to predict an individual's health, rather than as a statistical measurement for groups, the BMI has limitations that can make it less useful than some of the alternatives, especially when applied to individuals with abdominal obesity, short stature, or unusually high muscle mass.
- BMI Formula:

<p align="center">
  <img src="https://github.com/af4092/BMI-App/assets/24220136/08dce797-25b7-49c3-898e-2d0da6e96b72" alt="Image">
</p>

- BMI Chart:

<p align="center">
  <img src="https://github.com/af4092/BMI-App/assets/24220136/51d572df-2adc-48f4-bf04-665e1664b749" alt="Image">
</p>

- BMI Basic Categories:

<p align="center">
  <img src="https://github.com/af4092/BMI-App/assets/24220136/a12ee95c-5d70-45d2-83e4-0f7e7c2efd03" alt="Image">
</p>

- BMI Comparison application that allows users to enter individuals' BMI information, compare the BMI values using a bar chart, and export the chart along with the data to a Word document.
- GUI Application main view:

<p align="center">
  <img src="https://github.com/af4092/BMI-App/assets/24220136/4e37f51e-5f3e-4c08-8769-4d13cdceb529" alt="Image">
</p>

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

<p align="center">
  <img src="https://github.com/af4092/BMI-App/assets/24220136/2a535aef-0581-4ee9-aaf5-0fd6bb9142e6" alt="Image">
</p>

- Demo video showing test run

https://github.com/af4092/BMI-App/assets/24220136/073aa601-999e-4e66-a6cb-2a78e2efc56f

