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
    private double bet = 0;
    private boolean actionDone = true;

    //Constructors
    public PlayerHand() {}
    public PlayerHand(Player player) { this.player = player;}
    
    //Public methods
    public void setPlayer(Player player) {
        this.player = player;
        clearHand();
    }
    
    public double getBet() { return bet; }
    public Player getPlayer () {return player;}
    public void removePlayer() { this.player = null; clearHand(); }
    public void setActionDone (boolean actionDone) { this.actionDone = actionDone; }
    public boolean isActionDone () { return this.actionDone; }
    
    @Override
    public void clearHand() {
        super.clearHand();
        bet = 0;
        actionDone = true;
    }
    public void bet(double bet) {
        if (bet > 0) {
            this.bet += bet;
            player.changeBalance(-bet);
        }
    }

    public void payout(double multiplier) {
        if (player != null) {
            player.changeBalance(bet*multiplier);
            clearHand();
        }
    }
}


