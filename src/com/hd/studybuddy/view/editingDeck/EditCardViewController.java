package com.hd.studybuddy.view.editingDeck;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This class get the question and the answer that the user want to put on a new card, from two text fields, in the edit
 * card menu.
 */
public class EditCardViewController {
    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerTextField;
    @FXML
    public Button confirmBtn;
    private EditCardListener listener;
    private ArrayList<String> cardToEdit;

    public void setModel(ArrayList<String> model) {
        this.cardToEdit = model;
        questionTextField.setText(cardToEdit.get(0));
        answerTextField.setText(cardToEdit.get(1));
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.setTitle("StudyBuddy - Edit Card");
        stage.show();
    }

    public interface EditCardListener {
        /**
         * Returns the question and the answer of a card
         * @return a tuple containing the question and the answer of a card
         */
        ArrayList<String> getCard();
        /**
         * Edits the card to the current deck and closes EditCardView
         * @param question : card's question
         * @param answer : card's answer
         */
        void editConfirmBtnClicked(String question, String answer);
    }

    public void setEditCardListener(EditCardListener editCardListener) {
        this.listener = editCardListener;
    }

    @FXML
    public void confirmBtnClicked(ActionEvent actionEvent) {
        String question = questionTextField.getText();
        String answer = answerTextField.getText();
        listener.editConfirmBtnClicked(question, answer);
    }
}
