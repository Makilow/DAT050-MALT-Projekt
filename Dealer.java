import blackjack.models.CardDeck;

import java.util.LinkedList;
import java.util.List;

public class Dealer {
    private int reSuffel = 50;
    private CardDeck cardDeck;
    private Dealer dealer;
   // private Hand playerHand = new playerHand();  //Radera!
    public Dealer() {}
    LinkedList<Hand> playerHands = new LinkedList<>();
    public void startGame() {
        dealer = new Dealer();
        cardDeck = new CardDeck(7);
    }
    //Delar ut två kort till alla spelare som är med i handen
    public void newRound(LinkedList<Hand>playerHands){
        // Ligger dealerns hand i listan?
        for(int i=0; i<2; i++){                   //Dela all spelare 2 kort
            for(int j=0; j<playerHands.size(); j++){
                playerDealOne(playerHands.get(j));
            }
            if(i==0){
                dealerHand.add(cardDeck.next());
            }else if(i==1){
                //Ett kort upp och ner?
            }
        }
        if(dealer.handValu<10){  //Betala alla spelare som fått blackjac
            for(int i=0; i<playerHands.size(); i++) {
                 if (blackJack(playerHands.get(i))) {
                      playerHands.get(i).payout((playerHands.get(i).showBet()) * 2.5);
                      playerHands.get(i).muckCards();
                 }
            }
        }
    }
    public void playerDealOne(Hand playerHand) {    //För newRound och dealern
        if (cardDeck.percentageLeft() <= reSuffel) {
            cardDeck = new CardDeck(7);
        }
        playerHand.add(cardDeck.next());
    }
    public void playerHit(Hand playerHand) {
        if (cardDeck.percentageLeft() <= reSuffel) {
            cardDeck = new CardDeck(7);
        }
            playerHand.add(cardDeck.next());      //Adderar ett kort till spelarens hand
            if (playerHand.value() > 21) {
                playerHand.muchCards();
            }
            if (playerHand.getValue == 21){
             //   next playerHand    //Deala nästa hand
            }
    }
    public void doubleHand(Hand playerHand){
        if (cardDeck.percentageLeft() < reSuffel){
            cardDeck = new CardDeck(7);
        }
        playerHand.add(cardDeck.next());      //Adderar ett kort till spelarens hand
        if(playerHand.value() > 21) {
            playerHand.muchCards();
        }
    }
    public voic playerStand(Hand playerHand){
        //next playerHand          //Deala nästa hand
    }
    public boolean blackJack(Hand playerHand){
        if(playerHand.getNumerOfCards==2 && playerHand.getValue()==21) return true;
        else return false;
    }

    public void dealDealer(Hand dealerHand){
        while(dealerHand.value<17) {
            playerDealOne(dealerHand);
        }
        if(dealerHand.value>21){
            dealerLoses(LinkedList<Hand>playerHands);
        }else{
            endOfRound(LinkedList<Hand>playerHands);
        }
    }
    //Om dealern blir "tjock"
    public void dealerLoses(LinkedList<Hand>playerHands){
        for(int i=0; i<playerHands.size(); i++){
            playerHands.get(i).payout((playerHands.get(i).getBet())*2); //Spelaren vinner, dubbla insatsen
            playerHands.get(i).muchCards();
        }
    }
    public void endOfRound(LinkedList<Hand>playerHands){
        for(int i=0; i<playerHands.size(); i++){
            if(playerHands.get(i).getValue() > 16 && playerHands.get(i).getValue() == dealerHand.getValue()){
                playerHands.get(i).payout(playerHands.get(i).getBet()); //Lika, spelaren behåller sin insats
                playerHands.get(i).muchCards();
            }else if(playerHands.get(i).getValue()>dealerHand.getValue()){
                playerHands.get(i).payout((playerHands.get(i).getBet())*2); //Spelaren vinner, dubbla insatsen
                playerHands.get(i).muchCards();
            }else{
                playerHands.get(i).muchCards();      //Spelaren förlorar =(
            }
        }
    }
}