package blackjack.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Hand class containing each table positions player, cards and bets.
 * @author Tor Falkenberg
 * @Version 1.1
 */

public class PlayerHand extends Hand {
    private Player player;
    private double bet = 0;
    private boolean actionDone = true;

    /**
     * Constructor for PlayerHand
     * player is set to null
     */
    public PlayerHand() { this.player = null;}
    /**
     * Constructor for PlayerHand
     * @param player    A Player
     */
    public PlayerHand(Player player) { this.player = player;}
    
    /**
     * Set function, to replace the player
     * @param player    the new player
     */
    public void setPlayer(Player player) {
        this.player = player;
        clearHand();
    }
    /**
     * Get function, gets the bet
     * @return  the bet
     */
    public double getBet() { return bet; }
    /**
     * Get function, gets the player
     * @return  the player
     */
    public Player getPlayer () {return player;}
    /**
     * removePlayer function, sets the player to null and clears hand
     */
    public void removePlayer() { this.player = null; clearHand(); }
    /**
     * setActionDone function, sets the actionDone
     * @param actionDone    new bool for actionDone
     */
    public void setActionDone (boolean actionDone) { this.actionDone = actionDone; }
    /**
     * isActionDone gets the actionDone boolean
     * @return  if actionDone
     */
    public boolean isActionDone () { return this.actionDone; }
    /**
     * clearHand function, clears the hand 
     * cards, bet and actionDone -> true
     */
    @Override
    public void clearHand() {
        super.clearHand();
        bet = 0;
        actionDone = true;
    }
    /**
     * bet function, changes the bet by the value passed through
     * @param bet   the value to change bet by
     */
    public void bet(double bet) {
        this.bet += bet;
        player.changeBalance(-bet);
    }
    /**
     * payout function, pays the Player according to multiplier
     * 0 -> loses the bet
     * 1 -> no change
     * 2 -> 2x the bet
     * @param multiplier    multiplies the bet
     */
    public void payout(double multiplier) {
        if (player != null) {
            player.changeBalance(bet*multiplier);
            clearHand();
            bet = 0;
        }
    }
}


