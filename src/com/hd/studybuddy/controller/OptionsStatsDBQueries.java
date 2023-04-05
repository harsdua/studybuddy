package com.hd.studybuddy.controller;

import com.hd.studybuddy.model.Stats;


/**
 * This class handles the communication with the database for options and stats features
 */
public class OptionsStatsDBQueries {
    public static Stats retrieveStats(String username) {
        return new Stats(
                retrieveExistingDecksCount(username),
                retrieveExistingCardsCount(username),
                retrieveCreatedDecksCount(username),
                retrieveCreatedCardsCount(username),
                retrieveCardsStudiedCount(username)
        );
    }

    public static int retrieveExistingDecksCount(String username) {
        return 10;
    }

    public static int retrieveExistingCardsCount(String username) {
        return 15;
    }

    public static int retrieveCreatedDecksCount(String username) {
        return 20;
    }

    public static int retrieveCreatedCardsCount(String username) {
        return 30;
    }

    public static int retrieveCardsStudiedCount(String username) {
        return 40;
    }

    public static void changePassword(String username, String newPassword) {

    }
}
