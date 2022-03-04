package blackjack.models;

import java.util.*;
/**
 * CardDeck class Iterator of the Card Class.
 * @author Lukas Wigren
 */
public class CardDeck implements Iterator<Card> {
    private static final int DECK_SIZE = 52;
    private List<Card> deck = new ArrayList<Card>();
    private final int decks;
    private int index = 0;
    /**
     * Constructor for CardDeck
     * Initiates with one deck
     */
    public CardDeck() {
        this.decks = 1;
        generateCards();
    }
    /**
     * Constructor for CardDeck
     * @param decks number of decks created when initiated
     */
    public CardDeck(int decks) {
        this.decks = decks;
        generateCards();
    }
    private void generateCards() {
        for (int i = 0; i < decks ; i++) 
            for (Suit s : Suit.values()) 
                for (Rank r : Rank.values()) 
                    deck.add(new Card(s, r));
        Collections.shuffle(deck);
    }
    /**
     * percentageLeft function gets the percentage left of the deck in whole number 0-100
     * @return  the percentage left of the deck
     */
    public int percentageLeft() { return 100*(DECK_SIZE*decks-index)/(DECK_SIZE*decks); }
    /**
     * hasNext function check whether the CardDeck iterator has another card
     * @return  if there is another card
     */
    @Override
    public boolean hasNext() { return this.deck.size() > index; }
    /**
     * next function gets the next card in the deck
     * @return  the next card
     */
    @Override
    public Card next() {
        if (!hasNext()) { throw new NoSuchElementException(); }
        return this.deck.get(index++);
    }
}
