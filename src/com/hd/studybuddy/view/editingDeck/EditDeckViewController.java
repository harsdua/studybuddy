package com.hd.studybuddy.view.editingDeck;

import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.CardListViewCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;


/**
 * This Class handles the editing the deck window view and its buttons
 */
public class EditDeckViewController {
    @FXML
    public Button editCardBtn;
    @FXML
    public Button addCardBtn;
    @FXML
    public Button deleteCardBtn;
    @FXML
    public Button deleteDeckBtn;
    @FXML
    public Button editDeckBtn;
    @FXML
    public ListView<Card> cardsListView;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public Label errorLabel;
    private ArrayList<Card> cards;
    private EditDeckListener listener;

    public interface EditDeckListener {
        void refreshCardsList();
        /**
         * Opens EditCardView if a card was selected
         * @param selectedCard : card selected by the user
         */
        void editCardBtnClicked(Card selectedCard);

        /**
         * Opens AddCardView
         */
        void addCardBtnClicked();
        /**
         * Opens DeleteCardView if a card was selected
         * @param selectedCard : card selected by the user
         */
        void deleteCardBtnClicked(Card selectedCard);
        /**
         * Updates the current deck
         */
        void updateDeck();
    }

    public void setModel(ArrayList<Card> model) {
        this.cards = model;
        cardsListView.setStyle("-fx-font-size: 2em ;");
        cardsListView.getItems().addAll(cards);
        cardsListView.setCellFactory(new Callback<ListView<Card>, ListCell<Card>>() {
            @Override
            public ListCell<Card> call(ListView<Card> deckListView) {
                return new CardListViewCell();
            }
        });
        scrollPane.setContent(cardsListView);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    public void show(Stage stage, Parent root) {
        stage.setScene(new Scene(root));
        stage.setTitle("StudyBuddy - Edit Deck");
        stage.show();
    }

    public void setEditDeckListener(EditDeckListener editDeckListener) {
        this.listener = editDeckListener;
    }

    @FXML
    public void editCardBtnClicked(ActionEvent actionEvent) {
        listener.editCardBtnClicked(cardsListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void noToEditCardSelectedError() {
        errorLabel.setText("You must select a card before editing a card !");
    }

    @FXML
    public void addCardBtnClicked(ActionEvent actionEvent) {
        listener.addCardBtnClicked();
    }

    @FXML
    public void deleteCardBtnClicked(ActionEvent actionEvent) {
        listener.deleteCardBtnClicked(cardsListView.getSelectionModel().getSelectedItem());

    }

    @FXML
    public void noToDeleteCardSelectedError() {
        errorLabel.setText("You must select a card before deleting a card!");
    }

    @FXML
    public void refreshCardsList(ArrayList<Card> cards) {
        cardsListView.getItems().clear();
        cardsListView.getItems().addAll(cards);
    }

    @FXML
    public void onRefreshButtonClick(){
        listener.refreshCardsList();
    }

    @FXML
    public void onSaveButtonClick(){
        listener.updateDeck();
    }
}
