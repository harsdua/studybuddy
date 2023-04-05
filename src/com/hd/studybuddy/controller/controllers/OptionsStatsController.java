package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.view.OptionsStatsViewController;

import java.io.IOException;

public class OptionsStatsController extends Controller {
    private final OptionsStatsControllerListener listener;

    public OptionsStatsController(Stage stage, OptionsStatsControllerListener listener) {
        this.stage = stage;
        this.listener = listener;
    }
    @Override
    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/OptionsStatsView.fxml"));
            Parent root = loader.load();
            OptionsStatsViewController controller = loader.getController();
            controller.setOptionsStatsListener(new OptionsStatsViewController.OptionsStatsListener() {
                @Override
                public void onModifyPassword(String username, String newPassword) {
                    listener.modifyPassword(newPassword);
                }

                @Override
                public void onGoBackMenuClicked() {
                    listener.showMainMenu();
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }
    public interface OptionsStatsControllerListener {
        void showMainMenu();
        void modifyPassword(String newPassword);
    }
}
