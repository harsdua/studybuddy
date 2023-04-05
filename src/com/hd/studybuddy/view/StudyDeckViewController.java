package com.hd.studybuddy.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


/**
 * The controller for the Study deck
 */
public class StudyDeckViewController {

    @FXML
    private Label cardLabel;
    @FXML
    private Button backMenuButton;
    @FXML
    private Button veryGoodButton;
    @FXML
    private Button goodButton;
    @FXML
    private Button averageButton;
    @FXML
    private Button badButton;
    @FXML
    private Button veryBadButton;
    private StudyDeckListener listener;

    public interface StudyDeckListener {
        void onCardClicked();
        /**
         * Redirects the user to the MainMenu
         */
        void backMenuBtnClicked();
        /**
         * Set the priority to the current card and returns the question to the next card
         * @param priority : priority depending on the knowledge button clicked by the user
         */
        void knowledgeBtnClicked(int priority);
    }

    public void setModel(String firstQuestion) {
        cardLabel.setText(firstQuestion);
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setTitle("StudyBuddy - Study Deck");
    }

    public void setStudyDeckListener(StudyDeckListener studyDeckListener) {
        this.listener = studyDeckListener;
    }

    @FXML
    public void cardClicked(MouseEvent event) {
        listener.onCardClicked();
    }

    @FXML
    public void showCardAnswer(String answer) {
        cardLabel.setText(answer);
        buttonsVisibility(true);
    }

    @FXML
    public void backMenuBtnClicked(ActionEvent event) {
        listener.backMenuBtnClicked();
    }

    @FXML
    public void setNextCard(String question) {
        cardLabel.setText(question);
        buttonsVisibility(false);
    }

    @FXML
    private void buttonsVisibility(Boolean visibility) {
        veryGoodButton.setVisible(visibility);
        goodButton.setVisible(visibility);
        averageButton.setVisible(visibility);
        badButton.setVisible(visibility);
        veryBadButton.setVisible(visibility);
    }

    /**
     * These are different methods for the buttons (very esay-easy-average-bad-very bad)
     * @param event
     */
    @FXML
    public void veryGoodBtnClicked(ActionEvent event) {
        listener.knowledgeBtnClicked(1);
    }

    @FXML
    public void goodBtnClicked(ActionEvent event) {
        listener.knowledgeBtnClicked(2);
    }

    @FXML
    public void averageBtnClicked(ActionEvent event) {
        listener.knowledgeBtnClicked(3);
    }

    @FXML
    public void badBtnClicked(ActionEvent event) {
        listener.knowledgeBtnClicked(4);
    }

    @FXML
    public void veryBadBtnClicked(ActionEvent event) {
        listener.knowledgeBtnClicked(5);
    }

}