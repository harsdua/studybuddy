import com.hd.studybuddy.model.Card;
import com.hd.studybuddy.model.Deck;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TestDeck {

    Deck deck = new Deck("Test", new HashSet<>());

    @Test
    public void testGetTitle() {
        assertEquals(deck.getTitle(), "Test");
    }

    @Test
    public void testSetTitle() {
        deck.setTitle("Info");
        assertEquals(deck.getTitle(), "Info");
    }

    @Test
    public void testGetHashmapCards() {
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        Card card2 = new Card("Quel age a Anne-Marie", "15 ans");
        deck.addCard(card1);
        deck.addCard(card2);
        HashMap<Card, Integer> cards = new HashMap<>();
        cards.put(card1, 5);
        cards.put(card2, 5);
        assertEquals(deck.getHashmapCards(), cards);
    }

    @Test
    public void testGetCards() {
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        Card card2 = new Card("Quel age a Anne-Marie", "15 ans");
        deck.addCard(card1);
        deck.addCard(card2);
        Set<Card> cardHashSet = new HashSet<>();
        cardHashSet.add(card1);
        cardHashSet.add(card2);
        assertEquals(deck.getCards(), cardHashSet);
    }

    @Test
    public void testSetCards(){
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        Card card2 = new Card("Quel age a Anne-Marie", "15 ans");
        HashMap<Card, Integer> cards = new HashMap<>();
        cards.put(card1, 5);
        cards.put(card2, 5);
        deck.setCards(cards);
        assertEquals(deck.getHashmapCards(), cards);
    }

    @Test
    public void testGetTags() {
        HashSet<String> tags = new HashSet<>();
        String tag1 = "info";
        String tag2 = "algo";
        tags.add(tag1);
        tags.add(tag2);
        deck.addTag(tag1);
        deck.addTag(tag2);
        assertEquals(deck.getTags(), tags);
    }


    @Test
    public void testAddCard(){
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        Card card2 = new Card("Quel age a Anne-Marie", "15 ans");
        deck.addCard(card1);
        deck.addCard(card2);
        HashMap<Card, Integer> cards = new HashMap<>();
        cards.put(card1, 5);
        cards.put(card2, 5);
        assertEquals(deck.getHashmapCards(), cards);
    }

    @Test
    public void testRemoveCard(){
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        Card card2 = new Card("Quel age a Anne-Marie", "15 ans");
        deck.addCard(card1);
        deck.addCard(card2);
        deck.removeCard(card1);
        HashMap<Card, Integer> cards = new HashMap<>();
        cards.put(card2, 5);
        assertEquals(deck.getHashmapCards(), cards);
    }

    @Test
    public void testResetPriority(){
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        deck.addCard(card1);
        deck.changeCardPriority(card1, 0);
        deck.resetPriority();
        assertEquals(deck.getCardPriority(card1), 5);
    }

    @Test
    public void testChangeCardPriority() {
        Card card1 = new Card("Quel age a Nargis", "20 ans");
        deck.addCard(card1);
        deck.changeCardPriority(card1, 0);
        assertEquals(deck.getCardPriority(card1), 0);
    }

    @Test
    public void testAddTag(){
        String tag = "info";
        deck.addTag(tag);
        assertTrue(deck.getTags().contains(tag));
    }

    @Test
    public void testRemoveTag() {
        String tag = "info";
        deck.addTag(tag);
        deck.removeTag(tag);
        assertFalse(deck.getTags().contains(tag));
    }
}
