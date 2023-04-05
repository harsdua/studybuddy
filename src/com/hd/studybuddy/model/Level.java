package com.hd.studybuddy.model;

/**
 * This enumeration represents the level of knowledge of a card
 */

enum Level {
    VERY_EASY(1), EASY(2), MODERATE(3), HARD(4), VERY_HARD(5);

    private final int value;

    Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
