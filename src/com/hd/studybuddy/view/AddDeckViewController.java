package com.hd.studybuddy.view;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class makes the menu add deck functional, it'll get what the user put in the fields and show an error message
 * if it's empty.
 */
public class AddDeckViewController {
    @FXML
    private TextField deckNameTextField;
    @FXML
    private TextField tagsTextField;
    @FXML
    private Label errorAddDeckLabel;
    private AddDeckListener listener;

    public interface AddDeckListener {
        /**
         * Add a new deck to DeckManager
         * @param deckName : name of the new deck
         * @param tags : tags of yhe new deck
         */
        void onAddDeckClicked(String deckName, String tags);
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.setTitle("StudyBuddy - Add Deck");
        stage.show();
    }

    public void setAddDeckListener(AddDeckListener addDeckListener) {
        this.listener = addDeckListener;
    }

    /**
     * This methode is used to interact with the interface when we open the window for adding a deck, we need to give
     * a name and one or multiple tags.
     */
    @FXML
    public void onAddDeckClick() {
        String deckName = deckNameTextField.getText();
        String tags = tagsTextField.getText();
        listener.onAddDeckClicked(deckName, tags);
    }
    @FXML
    public void tagsEmptyError() {
        errorAddDeckLabel.setText("Error one or multiple fields are empty");
    }
}
