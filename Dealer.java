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
    public void newRound(List<Hand>playerHands){
        int playerBet;
        // Ligger dealerns hand i listan?
        for(int i=0; i<2; i++){                   //Dela all spelare 2 kort
            for(int j=0; j<playerHands.size; j++){
                playerDealOne(playerHands.get(j));
                if(j==1 && blackJack(playerHands.get(j))){     // Betala spelarn och mucha handen Black Jack!
                    playerHand.payout((playerHands.get(j).showBet())*2.5);
                    playerHand.muchCards();
                }
            }
        }
    }
    public void playerDealOne(Hand playerHand) {    //För newRound och dealern
        if (cardDeck.percentageLeft() < reSuffel) {
            cardDeck = new CardDeck(7);
        }
        playerHand.add(cardDeck.next());
    }
    public void playerHit(Hand playerHand) {
        if (cardDeck.percentageLeft() < reSuffel) {
            cardDeck = new CardDeck(7);
        }
            playerHand.add(cardDeck.next());      //Adderar ett kort till spelarens hand
            if (playerHand.value() > 21) {
                playerHand.muchCards();
            }
            if (playerHand.getValue == 21){
                next playerHand
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
        //next playerHand          //Deala nästa hand
    }
    public voic playerStand(Hand playerHand){
      //  if(next playerHand==true){
            //next playerHand          //Deala nästa hand
      //  }
     //   else {dealDealer();}
    }
    public boolean blackJack(Hand playerHand){
        if(playerHand.getNumerOfCards==2 && playerHand.getValue()==21) return true;
        else return false;
    }

    public void dealDealer(Hand dealerHand){
        while(dealerHand.value<17) {
            playerDealOne(dealerHand);
        }
        if(dealerHand.value<21){       //betala alla spelare vars händer är i spel!!
            for(int i=0; i<playerHands.size(); i++){
                playerHands.get(i).payout((playerHands.get(i).showBet)*2);
            }else{
                // kontrolera alla händer
            }

        }
    }

}