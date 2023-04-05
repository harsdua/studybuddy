package com.hd.studybuddy.model;
import java.io.Serializable;
import java.util.*;

/**
 * A class representing a Deck containing cards
 */
public class Deck implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private HashSet<String> tags = new HashSet<>();         // set of all tags given to the deck
    //TODO: Priority should be in StudyManager not as a deck attribute
    private HashMap<Card, Integer> cards = new HashMap<>();     // hashmap of all cards as key and their given priority as value

    /**
     * Constructor of the class Deck
     * @param title The name of the deck
     * @param tags The tags of the deck
     */
    public Deck(String title, HashSet<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    /**
     * Getter of the title of the deck
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter of the title of the deck
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    /**
     * Getter of the hashmap of the cards of the deck
     */
    public HashMap<Card, Integer> getHashmapCards() {
        return cards;
    }

    /**
     * Getter of the set of the cards of the deck
     */
    public Set<Card> getCards() {
        return cards.keySet();
    }

    /**
     * Getter of the priority of a card
     */
    public Integer getCardPriority(Card card) {
        return cards.get(card);
    }

    /**
     * Getter of the tags of the deck
     */
    public HashSet<String> getTags() {
        return tags;
    }

    /**
     * Setter of the cards of the deck
     */
    public void setCards(HashMap<Card, Integer> newCards) {
        cards = newCards;
    }

    /**
     * Add a card to the deck
     */
    public void addCard(Card card) {
        cards.put(card, Level.VERY_HARD.getValue());
    }

    /**
     * Remove a card from the deck
     */
    public void removeCard(Card card) {
        cards.remove(card);
    }

    /**
     * Change the priority of a card
     */
    public void changeCardPriority(Card card, Integer newPriority) {
        cards.put(card, newPriority);
    }

    /**
     * Reset the priority of all the cards of the deck
     */
    public void resetPriority() {
        cards.replaceAll((c, v) -> 5);
    }

    /**
     * Add a tag to the deck
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Remove a tag from the deck
     */
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public void deleteCard(Card currentCard) {
        cards.remove(currentCard);
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public int getCardCount(){
        return getCards().size();
    }

}
