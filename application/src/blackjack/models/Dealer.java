package blackjack.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Dealer class containing responsible for table.
 *
 * @author Tomas Alander (med ändringar av Tor Falkenberg)
 * @Version 1.0
 */

public class Dealer {

    LinkedList<Hand> playerHands = new LinkedList<>();
    private int reShuffle = 50;
    private CardDeck cardDeck;
    private Hand dealerHand = new Hand();

    public Dealer() {
    }

    public void startGame() {
        cardDeck = new CardDeck(7);
    }

    public void addSeat(Player player) {
        playerHands.add(new Hand(player));
    }

    public List getSeats() {
        return playerHands;
    }

    /**
     * Deal all players two cards, dealer gets 2 cards where the last one is upside down.
     * if player gets blackjack and dealer hand value < 10, playerHand call function payout.
     */
    public void newRound() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < playerHands.size(); j++) {
                playerDealOne(playerHands.get(j));
            }
            if (i == 0) {
                dealerHand.addCard(cardDeck.next());
            } else if (i == 1) {
            }
        }
        if (dealerHand.getValue() < 10) {
            for (int i = 0; i < playerHands.size(); i++) {
                if (blackJack(playerHands.get(i))) {
                    playerHands.get(i).payout(2.5);
                }
            }
        }
    }

    /**
     * Deal next card from cardDeck to player/dealerHand
     * @param playerHand
     */

    public void playerDealOne(Hand playerHand) {
        if (cardDeck.percentageLeft() <= reShuffle) {
            cardDeck = new CardDeck(7);
        }
        playerHand.addCard(cardDeck.next());
    }

    /**
     * Deal playerHand one card, if playerHandValue > 21 "muck" playerHand
     * if player handValue == 21 deal next player or dealer.
     * @param playerHand
     */

    public void playerHit(Hand playerHand) {
        if (cardDeck.percentageLeft() <= reShuffle) {
            cardDeck = new CardDeck(7);
        }
        playerHand.addCard(cardDeck.next());
        if (playerHand.getValue() > 21) {
            playerHand.payout(0);
        }
        if (playerHand.getValue() == 21) {
            //   next playerHand    //Deala nästa hand
        }
    }

    /**
     * If player handValu [9,11] and player doubles, call doubleBet and deal playerHand one card.
     * @param playerHand
     */
    public void doubleHand(Hand playerHand) {
        if (cardDeck.percentageLeft() < reShuffle) {
            cardDeck = new CardDeck(7);
        }
        playerHand.doubleBet();
        playerHand.addCard(cardDeck.next());
      /*
        if (playerHand.getValue() > 21) {
            playerHand.payout(0);
        }
      */
    }
    /**
     * Deal next player or call dealDealer
     * @param playerHand
     */
    public void playerStand(Hand playerHand) {
        /*
        if(playerHands.hasNext()){}
        else dealDealer;
         */
    }

    /**
     * if playerHand value == 21 and playerHand got two cards the player has blackjack!
     * @param playerHand
     * @return true if player or dealer has blackjack
     */
    public boolean blackJack(Hand playerHand) {
        if (playerHand.getValue() == 21 && playerHand.getCards().size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * dealer draw if handValu < 17 ,dealer stay if handValue [17,21]
     * if dealer gets blackjack call dealerBlackJack()
     * if dealer "busts" call dealerLoses
     * if dealer handValue [17,21] call endOfRound
     * @param dealerHand
     */
    public void dealDealer(Hand dealerHand) {
        while (dealerHand.getValue() < 17) {
            playerDealOne(dealerHand);
        }
        if (dealerHand.getValue() > 21) {
            dealerLoses();
        } else if (blackJack(dealerHand)) {
            dealerBlackJack();
        } else {
            endOfRound();
        }
    }

    /**
     * dealer got "busted", pay all players left in this hand and "muck" their hands
     */
    public void dealerLoses() {
        for (int i = 0; i < playerHands.size(); i++) {
            if (blackJack(playerHands.get(i))) {
                playerHands.get(i).payout(2.5);
            } else playerHands.get(i).payout(2);
        }
        dealerHand = new Hand();
    }
    /**
     * if player got blackjack it is a "draw/push" else player loses their bets.
     * check all hands fore insurance.
     * "Muck" hand after payout
     */
    public void dealerBlackJack() {
        for (int i = 0; i < playerHands.size(); i++) {
            if (blackJack(playerHands.get(i))) {
                playerHands.get(i).payout(1); 
            } else {
                playerHands.get(i).payout(0); 
            }
        }
        dealerHand = new Hand();
    }

    /**
     * Compare dealer hand against all players hands left in this hand
     * if dealer handValu == player handValue it is a "draw/push", player keeps their bets
     * if playre handValu > dealer handVale -> player wins
     * if playre handValu < dealer handVale -> dealer wins
     * "Muck" hand after payout
     */
    public void endOfRound() {
        for (int i = 0; i < playerHands.size(); i++) {
            if (blackJack(playerHands.get(i))) {
                playerHands.get(i).payout(2.5);
            } else if (playerHands.get(i).getValue() == dealerHand.getValue()) {
                playerHands.get(i).payout(1);
            } else if (playerHands.get(i).getValue() > dealerHand.getValue()) {
                playerHands.get(i).payout(2);
            } else {
                playerHands.get(i).payout(0);
            }
        }
        dealerHand = new Hand();
    }
}
















