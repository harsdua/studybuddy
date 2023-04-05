
package com.hd.studybuddy.controller.managers;

import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.Database;
import com.hd.studybuddy.model.Deck;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class handles the decks manipulation by communicating with the database and files
 */
public class DeckManager {

	String username;

	/**
	 ** Constructor of the class
	 * @param username The user who owns the deck
	 */
	public void initialize(String username) {
		this.username = username;
	}


	Database db = LoginManager.db;

	/**
	 ** Add a new deck into the database
	 * @param deck A deck
	 */
	public boolean addDeck(Deck deck) {
		return db.addDeck(username, deck);
	}

	/**
	 ** Remove the given deck from the database
	 * @param deckName the name of the deck
	 */
	public void deleteDeck(String deckName) {
		db.removeDeck(username, deckName);
	}

	/**
	 ** Remove the given deck from the database
	 * @param deck A deck
	 */
	public void deleteDeck(Deck deck) {
		deleteDeck(deck.getTitle());
	}


	public void updateExistingDeck(Deck deck){
		db.updateExistingDeck(username,deck);
	}

	//TODO: updateExistingDeckWithTitle

	/**
	 ** Get the user decks
	 */
	public ArrayList<Deck> retrieveAllDecks(){
		ArrayList<Deck> lastRetrievedDecks = db.retreiveAllDecksFromUser(username);
		return lastRetrievedDecks;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the content of a file to convert it to a deck
	 * @param sc : a scanner that reads the file
	 */
	public void createDeckFromFile(Scanner sc){

		String deckName = sc.nextLine();
		String tags = sc.nextLine();
		HashSet<String> tagsSet = new HashSet<>(Arrays.asList(tags.split(",")));
		Deck deck = new Deck(deckName, tagsSet);
		while (sc.hasNextLine()){
			String line = sc.nextLine();
			if(!line.isBlank()) {
				String[] c = line.split(";");
				Card card = new Card(c[0], c[1]);
				deck.addCard(card);
			}
		}
		addDeck(deck);
	}

	/**
	 * Save a deck in a file
	 * @param printWriter : a printWriter that writes in the file
	 * @param deck : the deck to write in the file
	 */
	public void createFileFromDeck(PrintWriter printWriter, Deck deck){

		printWriter.write(deck.getTitle() + "\n");
		String tags = deck.getTags().stream().map(Object::toString).collect(Collectors.joining(",")); //give something like "tag1,tag2,tag3"
		printWriter.write(tags + "\n");
		for (Card c: deck.getCards()){
			printWriter.write(c.getQuestion() + ";" + c.getAnswer() + "\n");
		}
	}
}
