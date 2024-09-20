module fi.tuni.prog3.trackingapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens fi.tuni.prog3.trackingapp to javafx.fxml;
    exports fi.tuni.prog3.trackingapp;
}
