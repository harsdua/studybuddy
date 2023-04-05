package com.hd.studybuddy.model;

import java.util.*;

public class StudyManager {

    private Card currentCard;
    private Deck currentDeck;
    private Stack<Card> history = new Stack<>();                    // Stack of cards that have been studied
    private ArrayList<Card> cardsToStudy;                           // List of cards that will show up


    /**
     * Constructor of the class StudyManager
     * @param deck : the deck that will be studied
     */
    public StudyManager(Deck deck){
        this.currentDeck = deck;
        cardsToStudy = new ArrayList<>(currentDeck.getCards());
        shuffleCards();
        currentCard = cardsToStudy.get(0);
    }

    /**
     * Getter of the current card
     */
    public Card getCurrentCard(){
        return currentCard;
    }

    /**
     * Getter of the history of the cards studied
     */
    public Stack<Card> getHistory(){
        return history;
    }

    /**
     * Getter of the deck studied
     */
    public Deck getDeck(){
        return currentDeck;
    }

    /**
     * Getter of the list of cards to study
     */
    public ArrayList<Card>  getCardsToStudy() {
        return cardsToStudy;
    }


    /**
     * Go to the next card
     */
    public Card goNext() {
        currentCard = cardsToStudy.get(0);
        history.push(currentCard);
        cardsToStudy.remove(0);
        if (cardsToStudy.isEmpty()) {
            return null;
        }
        currentCard = cardsToStudy.get(0);      // get next card to study
        return currentCard;
    }

    /**
     * Go to the previous card
     */
    public Card goBack(){
        if (history.isEmpty()) {
            return null;
        }
        cardsToStudy.add(0, currentCard);   // put back the previous card in the list of cards to study
        currentCard = history.pop();
        return currentCard;
    }

    /**
     * Shuffle the cards to study
     */
    public void shuffleCards() {
        Collections.shuffle(cardsToStudy);
    }

    /**
     * Set the priority of a card and put the hard cards in the list of cards to study
     */
    public void setPriorityToCard(Card card, Integer priority){
        if (Objects.equals(priority, Level.VERY_HARD.getValue()) || Objects.equals(priority, Level.HARD.getValue()) || Objects.equals(priority, Level.MODERATE.getValue())) {
            Random rand = new Random();
            int randomInd = rand.nextInt(1, cardsToStudy.size());       // set a random index in the next to come
            cardsToStudy.add(randomInd, card);
        }
        currentDeck.changeCardPriority(card, priority);
    }
}
