package com.hd.studybuddy.view.editingDeck;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DeleteCardViewController {
    @FXML
    public Button cancelBtn;
    @FXML
    public Button confirmBtn;
    private DeleteCardListener listener;

    public interface DeleteCardListener {
        /**
         * Deletes the card and closes DeleteCardView
         */
        void deleteConfirmBtnClicked();
        /**
         * Closes DeleteCardView
         */
        void cancelBtnClicked();
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.setTitle("StudyBuddy - Delete Card");
        stage.show();
    }

    public void setDeleteCardListener(DeleteCardListener deleteCardListener) {
        this.listener = deleteCardListener;
    }

    @FXML
    public void cancelBtnClicked(ActionEvent actionEvent) {
        listener.cancelBtnClicked();
    }

    @FXML
    public void confirmBtnClicked(ActionEvent actionEvent) {
        listener.deleteConfirmBtnClicked();
    }
}
