package com.hd.studybuddy.controller.managers;

import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.Deck;
import com.hd.studybuddy.model.StudyManager;


/**
 * Controller for the StudyDeckView
 */
public class StudyDeckManager {

    private StudyManager studyManager;
    private static Deck deck;

    /**
     * Initializes the controller class.
     */

    public StudyDeckManager(Deck deck) {
        studyManager = new StudyManager(deck);

    }

    public void setDeck(Deck deck) {
        StudyDeckManager.deck = deck;
    }


    /**
     * Sets the priority of the current card to the given value
     */
    public void setPriority(Integer priority){
        studyManager.setPriorityToCard(studyManager.getCurrentCard(), priority);
    }


    public String getQuestion(){
        return studyManager.getCurrentCard().getQuestion();
    }

    public String getAnswer() {
        return studyManager.getCurrentCard().getAnswer();
    }

    /**
     * Goes to the next card
     */
    public Boolean goNext() {
        Card c = studyManager.goNext();
        return c != null;
    }

}
