package blackjack.models;
/**
 * Suit enum, all the suits for the Cards in a deck
 * @author Lukas Wigren
 */
public enum Suit {
    HEART("hearts"),
    SPADE("spades"),
    DIAMOND("diamonds"),
    CLUB("clubs");

    private final String name;
    Suit(String name) {this.name = name;}
    
    /**
     * toString function, returns the current Suit in String
     * @return  Suit as a String
     */
    @Override
    public String toString() {return name;}
}
