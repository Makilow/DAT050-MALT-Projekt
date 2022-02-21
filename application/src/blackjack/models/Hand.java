package blackjack.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand class containing each table positions player, cards and bets.
 * @author Tor Falkenberg
 * @Version 1.1
 */

public class Hand {
    //Private variables
    private Player player;
    private List<Card> cardList = new ArrayList<>();
    private double bet;
    private boolean insured = false;


    //Constructors
    public Hand() {}
    public Hand(Player player) { this.player = player; }


    //Functions
    public void setPlayer(Player player) { this.player = player; }
    public void removePlayer() { this.player = null; }

    public void newHand() { cardList.clear(); }
    public void addCard(Card card) { cardList.add(card); }
    public List<Card> getCards() { return cardList; }

    public void bet(double bet) {
        this.bet += bet;
        player.changeBalance(-bet);
    }
    public double showBet() { return bet; }
    public void payout(double multiplier) {
        if (insured) {
            if(multiplier == 0) {
                multiplier = 1;
            } else {
                bet -= bet/3;
            }
        }
        player.changeBalance(bet*multiplier);
        cardList.clear();
        bet = 0;
        insured = false;
    }

    public int getValue() {
        int sum = 0;
        int aces = 0;
        for (Card card : cardList) {
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

    public void doubleBet() { bet(this.bet); }
    public Hand split() {
        Hand hand = new Hand(player);
        hand.bet(bet);
        hand.addCard(cardList.get(1));
        cardList.remove(1);
        return hand;
    }
    public void insure() {
        insured = true;
        bet(bet/2);
    }

     /*Test code

    public static void main(String[] args) {

        CardDeck deck = new CardDeck();
        Player Tor = new Player("Tor", 1000);
        Hand hand = new Hand(Tor);

        hand.addCard(deck.next());
        hand.addCard(deck.next());
        hand.addCard(deck.next());
        hand.addCard(deck.next());

        System.out.println("Player: " + Tor.getName());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        hand.bet(200);
        hand.doubleBet();

        System.out.println("Bet: " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        System.out.println("Cards: ");
        for(Card card : hand.getCards()) {
            System.out.println(card);
        }
        System.out.println("Total card value: " + hand.getValue());
        System.out.println();

        hand.payout(2);

        System.out.println("Bet: " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        Hand hand2 =  hand.split();

        System.out.println("Cards hand1: ");
        for(Card card : hand.getCards()) {
            System.out.println(card);
        }
        System.out.println("Total card value: " + hand.getValue());
        System.out.println();

        System.out.println("Cards hand2: ");
        for(Card card : hand2.getCards()) {
            System.out.println(card);
        }
        System.out.println("Total card value: " + hand2.getValue());
        System.out.println();


        hand.bet(200);
        hand.insure();

        System.out.println("Bet (200 + insurance): " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        hand.payout(0);

        System.out.println("*Dealer BlackJack*");
        System.out.println("Bet: " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        hand.bet(200);
        hand.insure();

        System.out.println("Bet (200 + insurance): " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        hand.payout(1);

        System.out.println("*Tie*");
        System.out.println("Bet: " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        hand.bet(200);
        hand.insure();

        System.out.println("Bet (200 + insurance): " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();

        hand.payout(2);

        System.out.println("*Player Win*");
        System.out.println("Bet: " + hand.showBet());
        System.out.println("Money: " + Tor.getBalance());
        System.out.println();
    }
*/
}


