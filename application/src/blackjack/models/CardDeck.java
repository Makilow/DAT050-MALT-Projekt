package blackjack.models;

import java.util.*;
/**
 * CardDeck class Iterator for the Card Class.
 * @author Lukas Wigren
 */
public class CardDeck implements Iterator<Card> {
    private static final int DECK_SIZE = 52;
    private List<Card> deck = new ArrayList<Card>();
    private final int decks;
    private int index = 0;
    public CardDeck() {
        this.decks = 1;
        generateCards();
    }
    public CardDeck(int decks) {
        this.decks = decks;
        generateCards();
    }
    private void generateCards() {
        for (int i = 0; i < decks ; i++) 
            for (Suit s : Suit.values()) 
                for (Rank r : Rank.values()) 
                    deck.add(new Card(s, r));
        Collections.shuffle(deck);  // Shuffle cards
    }
    public int percentageLeft() { return 100*(DECK_SIZE*decks-index)/(DECK_SIZE*decks); }  // returns % left of the deck
    @Override
    public boolean hasNext() { return this.deck.size() > index; }
    @Override
    public Card next() {
        if (!hasNext()) { throw new NoSuchElementException(); }
        return this.deck.get(index++);
    }
    @Override
    public void remove() { throw new UnsupportedOperationException(); }
}