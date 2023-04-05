package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.view.editingDeck.DeleteCardViewController;

import java.io.IOException;

public class DeleteCardController extends Controller {
    private final DeleteCardControllerListener listener;

    public DeleteCardController(Stage stage, DeleteCardControllerListener listener) {
        this.stage = stage;
        this.listener = listener;
    }
    @Override
    public void show() {
        try {
            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/DeleteCardView.fxml"));
            Parent root = loader.load();
            DeleteCardViewController controller = loader.getController();
            controller.setDeleteCardListener(new DeleteCardViewController.DeleteCardListener() {
                @Override
                public void deleteConfirmBtnClicked() {
                    listener.deleteCard();
                    stage.close();
                }
                @Override
                public void cancelBtnClicked() {
                    stage.close();
                }
            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }
    public interface DeleteCardControllerListener {
        void deleteCard();
    }
}
