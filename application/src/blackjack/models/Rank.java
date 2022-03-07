package blackjack.models;
/**
 * Rank enum, all the ranks for the Cards in a deck
 * @author Lukas Wigren
 */
public enum Rank {
    ACE("ace"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("jack"),
    QUEEN("queen"),
    KING("king");

    private final String name;
    Rank(String name) { this.name = name;}
    
    /**
     * toString function, returns the current Rank in String
     * @return  Rank as a String
     */
    @Override
    public String toString() {return name;}
}
