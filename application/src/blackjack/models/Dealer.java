package blackjack.models;

import blackjack.models.CardDeck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Dealer class containing responsible for table.
 * @author Tomas Alander (med ändringar av Tor Falkenberg)
 * @Version 1.0
 */

public class Dealer {

    private int reSuffel = 50;
    private CardDeck cardDeck;
    private Hand dealerHand = new Hand();
    LinkedList<Hand> playerHands = new LinkedList<>();

    public Dealer() {}

    public void startGame() {
        cardDeck = new CardDeck(7);
    }

    public void addSeat(Player player) {
        playerHands.add(new Hand(player));
    }

    public List getSeats() { return playerHands; }

    //Delar ut två kort till alla spelare som är med i rundan
    public void newRound(){
        // Ligger dealerns hand i listan?
        for(int i=0; i<2; i++){                   //Dela all spelare 2 kort
            for(int j=0; j<playerHands.size(); j++){
                playerDealOne(playerHands.get(j));
            }
            if(i==0){
                dealerHand.addCard(cardDeck.next());
            }else if(i==1){
                //Ett kort upp och ner?
            }
        }
        if(dealerHand.getValue()<10){  //Betala alla spelare som fått blackjac
            for(int i=0; i<playerHands.size(); i++) {
                if (blackJack(playerHands.get(i))) {
                    playerHands.get(i).payout(2.5);
                }
            }
        }
    }
    public void playerDealOne(Hand playerHand) {    //För newRound och dealern
        if (cardDeck.percentageLeft() <= reSuffel) {
            cardDeck = new CardDeck(7);
        }
        playerHand.addCard(cardDeck.next());
    }
    public void playerHit(Hand playerHand) {
        if (cardDeck.percentageLeft() <= reSuffel) {
            cardDeck = new CardDeck(7);
        }
        playerHand.addCard(cardDeck.next());      //Adderar ett kort till spelarens hand
        if (playerHand.getValue() > 21) {
            playerHand.payout(0);
        }
        if (playerHand.getValue() == 21){
            //   next playerHand    //Deala nästa hand
        }
    }
    public void doubleHand(Hand playerHand){
        if (cardDeck.percentageLeft() < reSuffel){
            cardDeck = new CardDeck(7);
        }
        playerHand.doubleBet();
        playerHand.addCard(cardDeck.next());      //Adderar ett kort till spelarens hand
        if(playerHand.getValue() > 21) {
            playerHand.payout(0);
        }
    }
    public void playerStand(Hand playerHand){
        //next playerHand          //Deala nästa hand
    }
    public boolean blackJack(Hand playerHand){
        if( playerHand.getValue()==21) {
            return true;
        } else {
            return false;
        }
    }

    public void dealDealer(Hand dealerHand){
        while(dealerHand.getValue()<17) {
            playerDealOne(dealerHand);
        }
        if(dealerHand.getValue()>21){
            dealerLoses();
        }else{
            endOfRound();
        }
    }
    //Om dealern blir "tjock"
    public void dealerLoses(){
        for(int i=0; i<playerHands.size(); i++){
            playerHands.get(i).payout(2); //Spelaren vinner, dubbla insatsen

        }
    }
    public void endOfRound(){
        for(int i=0; i<playerHands.size(); i++){
            if(playerHands.get(i).getValue() == dealerHand.getValue()){
                playerHands.get(i).payout(1); //Lika, spelaren behåller sin insats
            }else if(playerHands.get(i).getValue()>dealerHand.getValue() && playerHands.get(i).getValue()<=21){
                playerHands.get(i).payout(2); //Spelaren vinner, dubbla insatsen
            }else{
                playerHands.get(i).payout(0);      //Spelaren förlorar =(
            }
        }
    }
}
