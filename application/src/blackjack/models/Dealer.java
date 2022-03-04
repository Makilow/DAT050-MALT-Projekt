package blackjack.models;
/**
 * Dealer class, handles the deck and deals card
 * @author Lukas Wigren, Tomas Alander
 */
public class Dealer {
    private int decks;
    private CardDeck deck;
    /**
     * Dealer constructor, initiates the
     * @param decks number of decks to create
     */
    public Dealer(int decks) {
        this.decks = decks;
        deck = new CardDeck(decks);
    }
    /**
     * dealCard function, deals card when called
     * @return a card from the Dealers deck
     */
    public Card dealCard() {
        if (deck.percentageLeft() <= 50) { deck = new CardDeck(decks); }
        return deck.next();
    }
    /**
     * newDeck function
     * @param decks number of decks to create
     */
    public void newDeck(int decks) {
        this.decks = decks;
        deck = new CardDeck(decks);
    }
}
