module com.hd.studybuddy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.hd.studybuddy.view to javafx.fxml;
    exports com.hd.studybuddy.view;
    opens com.hd.studybuddy.controller to javafx.fxml;
    exports com.hd.studybuddy.controller;
    exports com.hd.studybuddy.model;
    opens com.hd.studybuddy.model to javafx.fxml;
    exports com.hd.studybuddy.view.editingDeck;
    opens com.hd.studybuddy.view.editingDeck to javafx.fxml;
    exports com.hd.studybuddy.controller.managers;
    opens com.hd.studybuddy.controller.managers to javafx.fxml;
    exports com.hd.studybuddy.controller.controllers;
    opens com.hd.studybuddy.controller.controllers to javafx.fxml;

}