package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.view.AddDeckViewController;

import java.io.IOException;

public class AddDeckController extends Controller {
    private final AddDeckControllerListener listener;

    public AddDeckController(Stage stage, AddDeckControllerListener listener) {
        this.stage = stage;
        this.listener = listener;
    }
    @Override
    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/AddDeckView.fxml"));
            Parent root = loader.load();
            AddDeckViewController controller = loader.getController();
            controller.setAddDeckListener(new AddDeckViewController.AddDeckListener() {
                @Override
                public void onAddDeckClicked(String deckName, String tags) {
                    if (deckName.isEmpty() || tags.isEmpty()) {
                        controller.tagsEmptyError();
                    }
                    else {
                        listener.addDeck(deckName, tags);
                        stage.close();
                    }
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }
    public interface AddDeckControllerListener {
        void addDeck(String deckName, String tags);
    }
}
