package fi.tuni.prog3.trackingapp;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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

    @FXML
    private void initialize() {
        deviceGroupComboBox.getItems().addAll("My Data", "Family", "Friends");
        dataTypeComboBox.getItems().addAll("Calories", "Steps", "Humidity");
        timeframeComboBox.getItems().addAll("Today", "This Week", "This Month");
        
        xAxis.setLabel("Time");
        yAxis.setLabel("Value");
    }

    @FXML
    private void selectGroup() {
        // Placeholder for future group selection logic
    }

    @FXML
    private void manageGroups() {
        System.out.println("Manage Groups button clicked.");
    }

    @FXML
    private void displayData() {
        lineChart.getData().clear();
        
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(deviceGroupComboBox.getValue() + " - " + dataTypeComboBox.getValue());
        
        series.getData().add(new XYChart.Data<>(1, 150)); 
        series.getData().add(new XYChart.Data<>(2, 200)); 
        series.getData().add(new XYChart.Data<>(3, 250)); 
        series.getData().add(new XYChart.Data<>(4, 300));
        
        lineChart.getData().add(series);
    }

    @FXML
    private void uploadData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                dataDisplayTextArea.setText(String.join("\n", lines));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void quitApplication() {
        System.exit(0);
    }
}
