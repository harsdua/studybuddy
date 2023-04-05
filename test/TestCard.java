import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.hd.studybuddy.model.Card;

public class TestCard {
    Card c = new Card("Quel âge à la Terre ?", "5 milliard");

    @Test
    public void testGetAnswer() {
        assertEquals("5 milliard", c.getAnswer());
    }

    @Test
    public void testSetAnswer() {
        c.setAnswer("4 milliard");
        assertEquals("4 milliard", c.getAnswer());
    }

    @Test
    public void testGetQuestion() {
        assertEquals("Quel âge à la Terre ?", c.getQuestion());
    }

    @Test
    public void testSetQuestion() {
        c.setQuestion("Quel est la durée de vie de la Terre ?");
        assertEquals("Quel est la durée de vie de la Terre ?", c.getQuestion());
    }
}
