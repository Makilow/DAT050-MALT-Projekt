package blackjack.models.tests;

import blackjack.models.Card;
import blackjack.models.Rank;
import blackjack.models.Suit;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @org.junit.jupiter.api.Test
    void getSuit() {
        Card c1 = new Card(Suit.CLUB, null);
        assertEquals(c1.getSuit(), Suit.CLUB);
        Card c2 = new Card(Suit.SPADE, null);
        assertEquals(c2.getSuit(), Suit.SPADE);
        Card c3 = new Card(Suit.HEART, null);
        assertEquals(c3.getSuit(), Suit.HEART);
        Card c4 = new Card(Suit.DIAMOND, null);
        assertEquals(c4.getSuit(), Suit.DIAMOND);
        Card c5 = new Card(null, null);
        assertNull(c5.getSuit());
    }

    @org.junit.jupiter.api.Test
    void getRank() {
        Card c1 = new Card(null, Rank.ACE);
        assertEquals(c1.getRank(), Rank.ACE);
        Card c2 = new Card(null, Rank.TWO);
        assertEquals(c2.getRank(), Rank.TWO);
        Card c3 = new Card(null, Rank.THREE);
        assertEquals(c3.getRank(), Rank.THREE);
        Card c4 = new Card(null, Rank.FOUR);
        assertEquals(c4.getRank(), Rank.FOUR);
        Card c5 = new Card(null, Rank.FIVE);
        assertEquals(c5.getRank(), Rank.FIVE);
        Card c6 = new Card(null, Rank.SIX);
        assertEquals(c6.getRank(), Rank.SIX);
        Card c7 = new Card(null, Rank.SEVEN);
        assertEquals(c7.getRank(), Rank.SEVEN);
        Card c8 = new Card(null, Rank.EIGHT);
        assertEquals(c8.getRank(), Rank.EIGHT);
        Card c9 = new Card(null, Rank.NINE);
        assertEquals(c9.getRank(), Rank.NINE);
        Card c10 = new Card(null, Rank.TEN);
        assertEquals(c10.getRank(), Rank.TEN);
        Card c11 = new Card(null, Rank.JACK);
        assertEquals(c11.getRank(), Rank.JACK);
        Card c12 = new Card(null, Rank.QUEEN);
        assertEquals(c12.getRank(), Rank.QUEEN);
        Card c13 = new Card(null, Rank.KING);
        assertEquals(c13.getRank(), Rank.KING);
        Card c14 = new Card(null, null);
        assertNull(c14.getRank());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Card c1 = new Card(Suit.HEART, Rank.QUEEN);
        Card c2 = new Card(Suit.HEART, Rank.QUEEN);
        assertEquals(c1, c2);
        Card c3 = new Card(null, null);
        Card c4 = new Card(Suit.CLUB, Rank.THREE);
        assertNotEquals(c3, c4);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Card c1 = new Card(null, null);
        assertNull(null, c1.toString());
        Card c2 = new Card(Suit.HEART, null);
        assertEquals("hearts "+ null, c2.toString());
        Card c3 = new Card(null, Rank.ACE);
        assertEquals(null +" ace", c3.toString());
        Card c4 = new Card(Suit.HEART, Rank.ACE);
        assertEquals("hearts ace", c4.toString());
    }
}
