package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.view.editingDeck.EditCardViewController;

import java.io.IOException;
import java.util.ArrayList;

public class EditCardController extends Controller {
    private final EditCardControllerListener listener;

    public EditCardController(Stage stage, EditCardControllerListener listener) {
        this.stage = stage;
        this.listener = listener;
    }
    @Override
    public void show() {
        try {
            ArrayList<String> model = listener.getCurrentCard();

            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/EditCardView.fxml"));
            Parent root = loader.load();

            EditCardViewController controller = loader.getController();
            controller.setModel(model);
            controller.setEditCardListener(new EditCardViewController.EditCardListener() {
                @Override
                public ArrayList<String> getCard() {
                    return listener.getCurrentCard();
                }
                @Override
                public void editConfirmBtnClicked(String question, String answer) {
                    listener.editCard(question, answer);
                    stage.close();
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }
    public interface EditCardControllerListener {
        ArrayList<String> getCurrentCard();
        void editCard(String question, String answer);
    }
}
