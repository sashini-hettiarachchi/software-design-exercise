package fi.tuni.prog3.trackingapp;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javafx.scene.text.Font;



public class PrimaryController {

    @FXML
    private ComboBox<String> deviceGroupComboBox;

    @FXML
    private ComboBox<String> dataTypeComboBox;

    @FXML
    private ComboBox<String> timeframeComboBox;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextArea dataDisplayTextArea;
    
    

    // Sample data for today and this week (initial hard-coded values)
    private int[] myStepsToday = {0, 20, 80, 300, 411, 550, 790, 932, 1074};
    private int[] myCaloriesToday = {75, 155, 300, 430, 511, 698, 785, 867, 915};
    private int[] timeToday = {2,4,6,8,10,12,14,16,18,20,22,24};
    
    private int[] myStepsWeek = {6500, 11025, 7450, 7923, 10002, 4999, 1074};
    private int[] myCaloriesWeek = {2000, 3140, 2300, 2560, 2990, 1780, 470};
    private int[] daysWeek = {1,2,3,4,5,6,7};
    
        private int[] familyStepsToday = {100, 300, 600, 900, 1200, 1500, 2000, 2500, 3000};
    private int[] familyCaloriesToday = {100, 200, 400, 600, 800, 1000, 1200, 1400, 1600};

    private int[] familyStepsWeek = {7000, 9000, 8500, 10000, 11000, 6000, 3500};
    private int[] familyCaloriesWeek = {2200, 3000, 2500, 3200, 3500, 1900, 1000};
    

    @FXML
    private void initialize() {
        deviceGroupComboBox.getItems().addAll("My_Data", "Family_Data");
        dataTypeComboBox.getItems().addAll("Calories", "Steps", "Both");
        timeframeComboBox.getItems().addAll("Today", "This Week");
        deviceGroupComboBox.setStyle("-fx-font-family: 'serif'");
        dataTypeComboBox.setStyle("-fx-font-family: 'serif'");
        timeframeComboBox.setStyle("-fx-font-family: 'serif'");
        
        xAxis.setLabel("Time");
        yAxis.setLabel("Value");
        xAxis.setTickLabelFont(Font.font("Arial"));
        yAxis.setTickLabelFont(Font.font("Arial"));
    }

    @FXML
    private void selectGroup() {
        // Placeholder for future group selection logic
    }

    @FXML
    private void manageGroups() {
        System.out.println("Manage Groups button clicked.");
    }

    private void drawStepsLine(int[] steps,int[] xLabels){
        
                XYChart.Series<Number, Number> stepsSeries = new XYChart.Series<>();
        stepsSeries.setName("Steps");
        for (int i = 0; i < steps.length; i++) {
            stepsSeries.getData().add(new XYChart.Data<>(xLabels[i], steps[i]));
        }
        lineChart.getData().add(stepsSeries);
            
    }
    
    private void drawCaloriesLine(int[] calories, int[] xLabels){
        
                 XYChart.Series<Number, Number> caloriesSeries = new XYChart.Series<>();
        caloriesSeries.setName("Calories");
        for (int i = 0; i < calories.length; i++) {
            caloriesSeries.getData().add(new XYChart.Data<>(xLabels[i], calories[i]));
        }
        lineChart.getData().add(caloriesSeries);
            
    }
    
    
    @FXML
    private void displayData() {
        // Clear the previous graph
        lineChart.getData().clear();
        
        // Get the selected data type (Steps, Calories, or Both) and timeframe (Today or This Week)
        String dataType = dataTypeComboBox.getValue();
        String timeframe = timeframeComboBox.getValue();
        String group = deviceGroupComboBox.getValue();
       

        int[] steps;
        int[] calories;
        int[] xLabels;

        
        if (group.equals("My_Data")) {
            steps = timeframe.equals("Today") ? myStepsToday : myStepsWeek;
            calories = timeframe.equals("Today") ? myCaloriesToday : myCaloriesWeek;
            xLabels = timeframe.equals("Today") ? timeToday : daysWeek;
        } else if (group.equals("Family_Data")) {
            steps = timeframe.equals("Today") ? familyStepsToday : familyStepsWeek;
            calories = timeframe.equals("Today") ? familyCaloriesToday : familyCaloriesWeek;
            xLabels = timeframe.equals("Today") ? timeToday : daysWeek;
        } else {
            return; // Invalid group selected
        }
        
        
        // Set the X-axis label based on timeframe
        if (timeframe.equals("Today")) {
            lineChart.getXAxis().setLabel("Hours");
        } else {
            lineChart.getXAxis().setLabel("Days of the Week");
        }
        // Draw the graph based on selected data type
        if (dataType.equals("Steps") || dataType.equals("Both")) {
            drawStepsLine(steps, xLabels);
        }
        if (dataType.equals("Calories") || dataType.equals("Both")) {
            drawCaloriesLine(calories, xLabels);
        }

        // Set Y-axis label
        yAxis.setLabel("Value");
    }

    
    
    @FXML
    private void uploadData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                
                // Assuming the file format is:
                
//                MyStepsToday: 0,20,80,300,411,550,790,932,1074
//MyCaloriesToday: 75,155,300,430,511,698,785,867,915
//MyStepsWeek: 6500,11025,7450,7923,10002,4999,1074
//MyCaloriesWeek: 2000,3140,2300,2560,2990,1780,470
//FamilyStepsToday: 0,20,80,300,411,550,790,932,1074
//FamilyCaloriesToday: 75,155,300,430,511,698,785,867,915
//FamilyStepsWeek: 6500,11025,7450,7923,10002,4999,1074
//FamilyCaloriesWeek: 2000,3140,2300,2560,2990,1780,470

                for (String line : lines) {
                    if (line.startsWith("MyStepsToday:")) {
                        myStepsToday = parseData(line);
                    } else if (line.startsWith("MyCaloriesToday:")) {
                        myCaloriesToday = parseData(line);
                    } else if (line.startsWith("MyStepsWeek:")) {
                        myStepsWeek = parseData(line);
                    } else if (line.startsWith("MyCaloriesWeek:")) {
                        myCaloriesWeek = parseData(line);
                    }else if (line.startsWith("FamilyStepsToday:")) {
                        myStepsToday = parseData(line);
                    } else if (line.startsWith("FamilyCaloriesToday:")) {
                        myCaloriesToday = parseData(line);
                    } else if (line.startsWith("FamilyStepsWeek:")) {
                        myStepsWeek = parseData(line);
                    } else if (line.startsWith("FamilyCaloriesWeek:")) {
                        myCaloriesWeek = parseData(line);
                    }
                }
                dataDisplayTextArea.setText("Data uploaded successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                dataDisplayTextArea.setText("Error uploading data.");
            }
        }
    }

    private int[] parseData(String line) {
        String[] parts = line.split(":")[1].split(",");
        int[] data = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            data[i] = Integer.parseInt(parts[i].trim());
        }
        return data;
    }

    @FXML
    private void quitApplication() {
        System.exit(0);
    }
}
