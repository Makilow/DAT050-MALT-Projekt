package blackjack.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand class containing each table positions player, cards and bets.
 * @author Tor Falkenberg
 * @Version 1.1
 */

public class PlayerHand extends Hand {
    //Private variables
    private Player player = null;
    //private final List<Card> cards = new ArrayList<>();
    private double bet = 0;
    private boolean insured = false;

    //Constructors
    public PlayerHand() {}
    public PlayerHand(Player player) { this.player = player;}


    //Functions
    public void setPlayer(Player player) {
        this.player = player;
        clearHand();
    }
    public double getBet() { return bet; }
    public Player getPlayer () {return player;}
    public void removePlayer() { this.player = null; }

    @Override
    public void clearHand() {
        super.clearHand();
        bet = 0;
        insured = false;
    }

    public void bet(double bet) {
        this.bet += bet;
        player.changeBalance(-bet);
    }



    public void payout(double multiplier) {
        if (insured) {
            if(multiplier == 0) {
                multiplier = 1;
            } else {
                bet -= bet/3;
            }
        }
        player.changeBalance(bet*multiplier);
        super.getCards().clear();
        bet = 0;
        insured = false;
    }

    public void doubleBet() { bet(this.bet); }
    public PlayerHand split() {
        PlayerHand hand = new PlayerHand(player);
        hand.bet(bet);
        hand.addCard(super.getCards().get(1));
        super.getCards().remove(1);
        return hand;
    }
    public void insure() {
        insured = true;
        bet(bet/2);
    }
}


