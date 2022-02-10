package blackjack.models;

/**
 * Card class, represents an individual card. Suit & Rank
 * @author Lukas Wigren
 */
public class Card {
    private final Suit suit;
    private final Rank rank;
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    public Suit getSuit() { return suit; }
    public Rank getRank() { return rank; }
    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Card card = (Card)o;
        return card.getSuit() == suit && card.getRank() == rank;
    }
    @Override
    public String toString() { return suit +" "+ rank; }
}