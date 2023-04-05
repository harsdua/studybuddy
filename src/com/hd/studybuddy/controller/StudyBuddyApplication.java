package com.hd.studybuddy.controller;

import com.hd.studybuddy.controller.controllers.LoginController;
import com.hd.studybuddy.controller.controllers.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application
 */
public class StudyBuddyApplication extends Application {

    private Stage mainStage;


    /**
     * This methode to open the window of our application with javafx.
     * @param primaryStage This is our first window
     */
    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        LoginController loginController = new LoginController(primaryStage, (username, loginManager) -> {
            MainMenuController mainMenuController = new MainMenuController(mainStage, username, loginManager);
            mainMenuController.show();
        });
        loginController.show();
    }

    /**
     * This is the main method which launch our application.
     */
    public static void main(String[] args) {
        launch();
    }

}

