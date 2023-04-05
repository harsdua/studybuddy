package com.hd.studybuddy.view;

import com.hd.studybuddy.controller.OptionsStatsDBQueries;
import com.hd.studybuddy.model.Stats;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class get information from the interface.
 */
public class OptionsStatsViewController {
    @FXML
    private TextField newPassTextField;
    @FXML
    private Label existingDecksCountLabel;
    @FXML
    private Label existingCardsCountLabel;
    @FXML
    private Label createdDecksCountLabel;
    @FXML
    private Label createdCardsCountLabel;
    @FXML
    private Label cardsStudiedCountLabel;
    private String username = "username";
    private OptionsStatsListener listener;

    public interface OptionsStatsListener {
        /**
         * Modifies the user's password
         * @param username : current username
         * @param newPassword : new password entered by the user
         */
        void onModifyPassword(String username, String newPassword);
        /**
         * Redirects the user to the MainMenu
         */
        void onGoBackMenuClicked();
    }

    public void show(Stage stage, Parent root) {
        stage.getScene().setRoot(root);
        stage.setWidth(420);
        stage.setHeight(550);
        stage.setTitle("StudyBuddy - Options & Stats");
    }

    public void setOptionsStatsListener(OptionsStatsListener optionsStatsListener) {
        this.listener = optionsStatsListener;
    }

    @FXML
    private void initialize(){
        Stats userStats = OptionsStatsDBQueries.retrieveStats(username);
        existingDecksCountLabel.setText(Integer.toString(userStats.getExistingDecksCount()));
        existingCardsCountLabel.setText(Integer.toString(userStats.getExistingCardsCount()));
        createdDecksCountLabel.setText(Integer.toString(userStats.getCreatedDecksCount()));
        createdCardsCountLabel.setText(Integer.toString(userStats.getCreatedCardsCount()));
        cardsStudiedCountLabel.setText(Integer.toString(userStats.getCardsStudiedCount()));
    }

    @FXML
    public void onConfirmNewPassClick() {
        listener.onModifyPassword(username, newPassTextField.getText());
    }

    @FXML
    public void onGoBackButtonClick() {
        listener.onGoBackMenuClicked();
    }
}
