package com.hd.studybuddy.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * A class representing a card
 */
public class Card implements Serializable {

    private String question;
    private String answer;

    /**
     * Constructor the class
     * @param question The question of the flashcard
     * @param answer The answer of the flashcard
     */
    public Card  (String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    /**
     * Setter of the question of the card
     */
    public void setQuestion(String question){
        this.question = question;
    }

    /**
     * Setter of the anwser of the card
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Getter of the anwser of the card
     */
    public String getAnswer(){
        return answer;
    }

    /**
     * Getter of the question of the card
     */
    public String getQuestion(){
        return question;
    }
}