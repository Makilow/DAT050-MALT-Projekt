package blackjack.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class Hand
 * A hand used to store cards
 * @author Tor Falkenberg
 */
public abstract class Hand {
    private final List<Card> cards = new ArrayList<>();
    /**
     *  clearHand function, clears the cards in hand
     */
    public void clearHand() { cards.clear(); }
    /**
     * addCard function adds a card to the hand
     * @param card  card to add to hand
     */
    public void addCard(Card card) { cards.add(card); }
    /**
     * getCards function gets the list of cards in the hand
     * @return  the list of cards
     */
    public List<Card> getCards() { return cards; }
    /**
     * getValue function gets the value of all cards in the list
     * @return  the value of all cards
     */
    public int getValue() {
        int sum = 0;
        int aces = 0;
        for (Card card : cards) {
            switch (card.getRank()) {
                case ACE -> {
                    sum += 11;
                    aces += 1;
                }
                case TWO -> sum += 2;
                case THREE -> sum += 3;
                case FOUR -> sum += 4;
                case FIVE -> sum += 5;
                case SIX -> sum += 6;
                case SEVEN -> sum += 7;
                case EIGHT -> sum += 8;
                case NINE -> sum += 9;
                case TEN, JACK, QUEEN, KING -> sum += 10;
            }
        }
        while (sum>21 && aces>0) {
            sum -= 10;
            aces--;
        }
        return sum;
    }
}
