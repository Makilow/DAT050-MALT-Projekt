package blackjack.models;

/**
 * Card class, represents an individual card. Suit & Rank
 * @author Lukas Wigren
 */
public class Card {
    private final Suit suit;
    private final Rank rank;
    /**
     * Constructor for Card
     * @param suit  Enum class Suit
     * @param rank  Enum class Rank
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    /**
     * Get function for the suit of the card
     * @return the suit of the card
     */
    public Suit getSuit() { return suit; }
    /**
     * Get function for the rank of the card
     * @return the rank of the card
     */
    public Rank getRank() { return rank; }
    /**
     * Equal function, checks whether the object in param
     * is equal to the object the function is being called in
     * @param o object that is compared
     * @return  if the objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        Card card = (Card)o;
        return card.getSuit() == suit && card.getRank() == rank;
    }
    /**
     * toString function, turns the object into a String
     * @return  the string equivalent of the object "suit rank"
     */
    @Override
    public String toString() { return suit +" "+ rank; }
}
