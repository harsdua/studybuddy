package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.view.editingDeck.AddCardViewController;

import java.io.IOException;

public class AddCardController extends Controller {
    private final AddCardControllerListener listener;

    public AddCardController(Stage stage, AddCardControllerListener listener) {
        this.stage = stage;
        this.listener = listener;
    }
    @Override
    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/AddCardView.fxml"));
            Parent root = loader.load();
            AddCardViewController controller = loader.getController();
            controller.setAddCardListener(new AddCardViewController.AddCardListener() {
                @Override
                public void createBtnClicked(String question, String answer) {
                    listener.addCard(question, answer);
                    stage.close();
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }
    public interface AddCardControllerListener {
        void addCard(String question, String answer);
    }
}
