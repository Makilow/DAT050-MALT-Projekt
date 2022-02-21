import blackjack.models.CardDeck;
import blackjack.models.Hand;
import java.util.LinkedList;
import java.util.List;

public class Dealer {
    private int reshuffle;
    private CardDeck cardDeck;
    private Dealer dealer;
    private Hand dealerHand;
    //private Hand playerHand = new playerHand();  //Radera!
    //LinkedList<Hand> playerHands = new LinkedList<Hand>();
    public Dealer() {
        reshuffle = 50;
    }
    public Dealer(int reSuffel) {
        if(reSuffel < 80 && reSuffel >= 0)
            this.reshuffle = reSuffel;
        else reshuffle = 50;
    }

    public void startGame() {
        dealer = new Dealer();
        cardDeck = new CardDeck(7);
        dealerHand = new Hand();
    }
    //Delar ut två kort till alla spelare som är med i handen
    public void newRound(LinkedList<Hand>playerHands){
        // Ligger dealerns hand i listan?
        // Hämta lista med aktuella händer
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
        if(dealer.handValu<10){  //Betala alla spelare som fått blackjack!
            for(int i=0; i<playerHands.size(); i++) {
                 if (blackJack(playerHands.get(i))) {
                      playerHands.get(i).payout((playerHands.get(i).showBet()) * 2.5);
                      newHand(playerHands.get(i));
                 }
            }
        }
    }
    public void playerDealOne(Hand playerHand) {    //För newRound och dealern
        if (cardDeck.percentageLeft() <= reshuffle) {
            cardDeck = new CardDeck(7);
        }
        playerHand.add(cardDeck.next());
    }
    public void playerHit(Hand playerHand) {
        if (cardDeck.percentageLeft() <= reshuffle) {
            cardDeck = new CardDeck(7);
        }
            playerHand.add(cardDeck.next());      //Adderar ett kort till spelarens hand
            if (playerHand.value() > 21) {
                newHand(playerHand);
            }
            if (playerHand.getValue == 21){
             //   next playerHand    //Deala nästa hand
            }
    }
    public void doubleHand(Hand playerHand){
        if (cardDeck.percentageLeft() <= reshuffle){
            cardDeck = new CardDeck(7);
        }
        playerHand.add(cardDeck.next());      //Adderar ett kort till spelarens hand
        if(playerHand.value() > 21) {
            newHand(playerHand);
        }
    }
    public void playerStand(LinkedList<Hand>playerHands){
        if(playerHands.hasNext){
            //nästa spelares tur!
        }else dealDealer(dealerHand);
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
            if(blackJack(dealerHand)) dealerBlackJack(LinkedList<Hand>playerHands);
            else endOfRound(LinkedList<Hand>playerHands);
        }
    }
    //Om dealern blir "tjock"
    public void dealerLoses(LinkedList<Hand>playerHands){
        for(int i=0; i<playerHands.size(); i++){
            if (blackJack(playerHands.get(i))) {
                playerHands.get(i).payout((playerHands.get(i).showBet()) * 2.5);
                newHand(playerHands.get(i));
            } else {
                playerHands.get(i).payout((playerHands.get(i).getBet()) * 2); //Spelaren vinner, dubbla insatsen
                newHand(playerHands.get(i));
            }
        }
        dealerHand = new Hand();
    }
    public void dealerBlackJack(LinkedList<Hand>playerHands){
        //Kontrollera försäkring och eventuell blackjack
        for(int i=0; i<playerHands.size(); i++){
            if(blackJack(playerHands.get(i))){
                playerHands.get(i).payout(playerHands.get(i).getBet()); //Lika, spelaren behåller sin insats
                newHand(playerHands.get(i));
            }else if (playerHands.get(i).getInsured()){
                // do something,
                newHand(playerHands.get(i));
            }else  newHand(playerHands.get(i));     //Spelaren förlorar =(
        }
        dealerHand = new Hand();
    }
    public void endOfRound(LinkedList<Hand>playerHands){
        for(int i=0; i<playerHands.size(); i++) {
            if (blackJack(playerHands.get(i))) {
                playerHands.get(i).payout((playerHands.get(i).showBet()) * 2.5);
                newHand(playerHands.get(i));
            } else {
                if (playerHands.get(i).getValue() == dealerHand.getValue()) {
                    playerHands.get(i).payout(playerHands.get(i).getBet()); //Lika, spelaren behåller sin insats
                    newHand(playerHands.get(i));
                } else if (playerHands.get(i).getValue() > dealerHand.getValue()) {
                    playerHands.get(i).payout((playerHands.get(i).getBet()) * 2); //Spelaren vinner, dubbla insatsen
                    newHand(playerHands.get(i));
                } else {
                    newHand(playerHands.get(i));     //Spelaren förlorar =(
                }
            }
        }
        dealerHand = new Hand();
    }
    public void newHand(Hand playerHand){
        playerHand = new Hand();
    }
}