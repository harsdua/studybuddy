package com.hd.studybuddy.view.editingDeck;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This Class represent the view of the creation of a new card.
 */
public class AddCardViewController {
    @FXML
    public TextField questionTextField;
    @FXML
    public TextField answerTextField;
    @FXML
    public Button createBtn;
    private AddCardListener listener;

    public interface AddCardListener {
        /**
         * Adds a card to the current deck and closes AddCardView
         * @param question : card's question
         * @param answer : card's answer
         */
        void createBtnClicked(String question, String answer);
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.setTitle("StudyBuddy - Add Card");
        stage.show();
    }

    public void setAddCardListener(AddCardListener addCardListener) {
        this.listener = addCardListener;
    }

    /**
     * This method is used to creat the button for the creation of a new card.
     * @param actionEvent
     */
    @FXML
    public void createBtnClicked(ActionEvent actionEvent) {
        String question = questionTextField.getText();
        String answer = answerTextField.getText();
        listener.createBtnClicked(question, answer);
    }
}
