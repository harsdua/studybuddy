package com.hd.studybuddy.controller.controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class Controller {
    protected Stage stage;
    public abstract void show();
    protected void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("StudyBuddy - Display Error");
        alert.setHeaderText("An essential file was not found.");
        alert.setContentText("It is a system error. Please contact the development team.");
        alert.showAndWait();
    }
}
