package blackjack.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
    private final List<Card> cards = new ArrayList<>();


    public void clearHand() { cards.clear(); }
    public void addCard(Card card) { cards.add(card); }
    public List<Card> getCards() { return cards; }
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
