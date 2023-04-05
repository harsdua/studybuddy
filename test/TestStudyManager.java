import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.Deck;
import com.hd.studybuddy.model.StudyManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


public class TestStudyManager {

    public Card card1 = new Card("Quel est le nombre de pi", "3,14");
    public Card card2 = new Card("Quel est le nombre d'Avogadro", "6.022");
    public Card card3 = new Card("Est ce que Pluton est une planète ", "Non");
    public Card card4 = new Card("Complexité de recherche binaire", "O(log(n))");
    public Deck deck;
    public StudyManager studyManager;

    @BeforeEach
    public void setStudyManager() {
        deck = new Deck("Test", new HashSet<>());
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card4);
        studyManager = new StudyManager(deck);
        deck.addCard(card3);

    }

    @Test
    public void testDeck() {
        // Test if changing the deck alone will change the deck attribute of the studyManager
        assertTrue(studyManager.getDeck().getCards().contains(card3));
        deck.removeCard(card3);
        assertFalse(studyManager.getDeck().getCards().contains(card3));
    }

    @Test
    public void testGoNext() {
        ArrayList<Card> toStudy = studyManager.getCardsToStudy();
        Card c = studyManager.goNext();
        assertEquals(c, toStudy.get(0));
        assertTrue(deck.getCards().contains(c));
    }

    @Test
    public void testShuffleCards(){
        studyManager.shuffleCards();
        for (Card c : studyManager.getCardsToStudy()) {
            assertTrue(deck.getCards().contains(c));
        }
    }

    @Test
    public void testSetPriorityToCard(){
        studyManager.setPriorityToCard(card1, 2);
        Deck d = studyManager.getDeck();
        assertEquals(d.getCardPriority(card1), 2);
    }

    @Test
    public void testGoBack() {
        Card c = studyManager.goNext();
        studyManager.goBack();
        Card c2 =  studyManager.goNext();
        assertEquals(c, c2);
    }

    @Test
    public void testGetHistory() {
        Card c1 = studyManager.getCurrentCard();
        Card c2 = studyManager.goNext();
        studyManager.goNext();
        Stack<Card> h = studyManager.getHistory();
        assertTrue(h.contains(c1));
        assertTrue(h.contains(c2));
    }

    @Test
    public void testGetDeck(){
        assertEquals(studyManager.getDeck(), deck);
    }

    @Test
    public void testGetCurrentCard() {
        Card c = studyManager.getCurrentCard();
        assertTrue(deck.getCards().contains(c));
    }

    @Test
    public void testGetCardsToStudy(){
        ArrayList<Card> toStudy = studyManager.getCardsToStudy();
        assertTrue(toStudy.contains(card1));
        assertTrue(toStudy.contains(card2));
    }

    @Test
    public void testFailedCards() {
        Card c = studyManager.getCurrentCard();
        studyManager.setPriorityToCard(c, 4);
        studyManager.goNext();
        assertTrue(studyManager.getCardsToStudy().contains(c));
        assertTrue(studyManager.getCardsToStudy().contains(card1));
        assertTrue(studyManager.getCardsToStudy().contains(card2));
        assertTrue(studyManager.getCardsToStudy().contains(card4));
    }

}
