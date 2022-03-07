package blackjack.models.tests;

import blackjack.models.Card;
import blackjack.models.CardDeck;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {
    @Test
    void percentageLeft() {
        CardDeck cd = new CardDeck();
        assertEquals(100, cd.percentageLeft());
        for (int i = 0; i < 26; i++) {
            cd.next();
        }
        assertEquals(50, cd.percentageLeft());
        while (cd.hasNext()) {
            cd.next();
        }
        assertEquals(0, cd.percentageLeft());

        cd = new CardDeck(5);
        assertEquals(100, cd.percentageLeft());
        for (int i = 0; i < 130; i++) {
            cd.next();
        }
        assertEquals(50, cd.percentageLeft());
        while (cd.hasNext()) {
            cd.next();
        }
        assertEquals(0, cd.percentageLeft());
    }

    @Test
    void hasNext() {
        CardDeck cd = new CardDeck();
        assertTrue(cd.hasNext());
        while(cd.hasNext()) {
            cd.next();
        }
        assertFalse(cd.hasNext());
    }

    @Test
    void next() {
        CardDeck cd = new CardDeck();
        Card c = new Card(null,null);
        assertNotNull(cd.next().getSuit());
        assertNotNull(cd.next().getRank());
        while(cd.hasNext()) {
            assertEquals(c.getClass(), cd.next().getClass());
        }
        assertThrows(NoSuchElementException.class, cd::next);
    }
}