package com.hd.studybuddy.controller.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.hd.studybuddy.controller.StudyBuddyApplication;
import com.hd.studybuddy.controller.managers.DeckManager;
import com.hd.studybuddy.controller.managers.EditDeckManager;
import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.Deck;
import com.hd.studybuddy.view.editingDeck.EditDeckViewController;

import java.io.IOException;
import java.util.ArrayList;

public class EditDeckController extends Controller {
    private final EditDeckManager editDeckManager;
    private final DeckManager deckManager;
    private final Deck currentDeck;

    public EditDeckController(Stage stage, Deck deck, DeckManager deckManager) {
        this.stage = stage;
        this.editDeckManager = new EditDeckManager(deck);
        this.deckManager = deckManager;
        this.currentDeck = deck;
    }
    @Override
    public void show() {
        try {
            ArrayList<Card> model = new ArrayList<>(currentDeck.getCards());

            FXMLLoader loader = new FXMLLoader(StudyBuddyApplication.class.getResource("/com/hd/studybuddy/studybuddy/resources/EditDeckView.fxml"));
            Parent root = loader.load();

            EditDeckViewController controller = loader.getController();
            controller.setModel(model);
            controller.setEditDeckListener(new EditDeckViewController.EditDeckListener() {
                @Override
                public void refreshCardsList() {
                    controller.refreshCardsList(editDeckManager.getCards());
                }
                @Override
                public void editCardBtnClicked(Card selectedCard) {
                    editDeckManager.setCurrentCard(selectedCard);
                    if (editDeckManager.currentCardIsEmpty()) {
                        controller.noToEditCardSelectedError();
                    }
                    else {
                        openEditCardWindow();
                    }
                }
                @Override
                public void addCardBtnClicked() {
                    openAddCardWindow();
                }
                @Override
                public void deleteCardBtnClicked(Card selectedCard) {
                    editDeckManager.setCurrentCard(selectedCard);
                    if (editDeckManager.currentCardIsEmpty()) {
                        controller.noToDeleteCardSelectedError();
                    }
                    else {
                        openDeleteCardWindow();
                    }
                }
                @Override
                public void updateDeck() {
                    editDeckManager.updateDeck(deckManager);
                }

            });
            controller.show(stage, root);
        } catch (IOException e) {
            showAlert();
        }
    }

    private void openEditCardWindow() {
        Stage newStage = new Stage();
        EditCardController editCardController = new EditCardController(newStage, new EditCardController.EditCardControllerListener() {
            @Override
            public ArrayList<String> getCurrentCard() {
                ArrayList<String> card = new ArrayList<>();
                card.add(editDeckManager.getQuestion());
                card.add(editDeckManager.getAnswer());
                return card;
            }

            @Override
            public void editCard(String question, String answer) {
                editDeckManager.editCard(question, answer);
                show();
            }
        });
        editCardController.show();
    }

    private void openAddCardWindow() {
        Stage newStage = new Stage();
        AddCardController addCardController = new AddCardController(newStage, new AddCardController.AddCardControllerListener() {
            @Override
            public void addCard(String question, String answer) {
                editDeckManager.addCard(question, answer);
                show();
            }
        });
        addCardController.show();
    }

    private void openDeleteCardWindow() {
        Stage newStage = new Stage();
        DeleteCardController deleteCardController = new DeleteCardController(newStage, new DeleteCardController.DeleteCardControllerListener() {
            @Override
            public void deleteCard() {
                editDeckManager.deleteCard();
                show();
            }
        });
        deleteCardController.show();
    }

}
