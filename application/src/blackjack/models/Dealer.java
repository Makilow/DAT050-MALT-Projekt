package blackjack.models;

public class Dealer {
    private int decks;
    private CardDeck deck;
    public Dealer(int decks) {
        this.decks = decks;
        deck = new CardDeck(decks);
    }
    public Card dealCard() {
        if (deck.percentageLeft() <= 50) { deck = new CardDeck(decks); }
        return deck.next();
    }
    public void newDeck(int decks) {
        this.decks = decks;
        deck = new CardDeck(decks);
    }
}
