package com.hd.studybuddy.model;

/**
 * This class represents the statistics of the user
 */
public class Stats {
    private int existingDecksCount;
    private int existingCardsdCount;
    private int createdDecksCount;
    private int createdCardsCount;
    private int cardsStudiedCount;

    public Stats(int existingDecksCount, int existingCardsdCount, int createdDecksCount, int createdCardsCount, int cardsStudiedCount) {
        this.existingDecksCount = existingDecksCount;
        this.existingCardsdCount = existingCardsdCount;
        this.createdDecksCount = createdDecksCount;
        this.createdCardsCount = createdCardsCount;
        this.cardsStudiedCount = cardsStudiedCount;
    }


    public int getExistingDecksCount() {
        return existingDecksCount;
    }

    public int getExistingCardsCount() {
        return existingCardsdCount;
    }

    public int getCreatedDecksCount() {
        return createdDecksCount;
    }

    public int getCreatedCardsCount() {
        return createdCardsCount;
    }

    public int getCardsStudiedCount() {
        return cardsStudiedCount;
    }

    public void setExistingDecksCount(int existingDecksCount) {
        this.existingDecksCount = existingDecksCount;
    }

    public void setExistingCardsdCount(int existingCardsdCount) {
        this.existingCardsdCount = existingCardsdCount;
    }

    public void setCreatedDecksCount(int createdDecksCount) {
        this.createdDecksCount = createdDecksCount;
    }

    public void setCreatedCardsCount(int createdCardsCount) {
        this.createdCardsCount = createdCardsCount;
    }

    public void setCardsStudiedCount(int cardsStudiedCount) {
        this.cardsStudiedCount = cardsStudiedCount;
    }

    public void incrementExistingDecksCount() {
        this.existingDecksCount++;
    }

}
