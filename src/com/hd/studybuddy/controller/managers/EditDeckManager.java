package com.hd.studybuddy.controller.managers;

import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.Deck;

import java.util.ArrayList;

/**
 * This method makes the controller for the Edit Deck.
 */
public class EditDeckManager {
    private Card currentCard;
    private final Deck currentDeck;

    public EditDeckManager(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<>(currentDeck.getCards());
    }

    /*
    edit card
     */
    public String getQuestion() {
        return currentCard.getQuestion();
    }

    public String getAnswer() {
        return currentCard.getAnswer();
    }

    public void editCard(String question, String answer) {
        currentCard.setQuestion(question);
        currentCard.setAnswer(answer);
    }

    /*
    add card
     */
    public void addCard(String question, String answer) {
        currentDeck.addCard(new Card(question, answer));
    }

    /*
    delete card
     */
    public void deleteCard() {
        currentDeck.deleteCard(currentCard);

    }

    public void updateDeck(DeckManager deckManager) {
        deckManager.updateExistingDeck(currentDeck);
    }

    public boolean currentCardIsEmpty() {
        return currentCard == null;
    }
}
