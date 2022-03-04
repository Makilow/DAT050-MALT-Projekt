package blackjack.models;

import blackjack.Observable;
import blackjack.Observer;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import javax.swing.Timer;

/**
 * MainModel, represents a Model as per MVC-design
 * @author Lukas Wigren, Tomas Alander, Tor Falkenberg
 */
public class MainModel implements Observable<MainModel> {

    //Local Setting Variables
    private int width=1280, height=720;
    private State state;
    private final Collection<Observer<MainModel>> observers = new HashSet<>();
    private boolean isFullscreen = false;
    private boolean soundON = true;
    private int nrOfPlayers = 5;            //Default number of players = 5
    private int timeBetweenRounds = 15;     //Default number of seconds between rounds

    //Local Game Variables
    private Dealer dealer;
    private DealerHand dealerHand;
    private List<PlayerHand> playerHandList;
    private boolean activeGame, playerActionsNeeded, showSecond;
    private int timerCounter;


    //Constructor
    public MainModel() { state = State.MENU; }

    // Screen & Setting methods
    public void setSize(int width, int height) {this.width=width; this.height=height; updateObservers();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void setState(State state) {
        if (state == State.GAME) {startGame();}
        this.state = state;
        updateObservers();
    }
    public State getState() { return state; }
    public boolean getIsFullscreen() { return isFullscreen; }
    public void toggleFullscreen() { isFullscreen ^= true; updateObservers(); }
    public boolean getSoundON() { return soundON; }
    public void toggleSound() { soundON ^= true; }
    public void setNrOfPlayers (int nrOfPlayers) { this.nrOfPlayers = nrOfPlayers; }
    public void setTimeBetweenRounds (int timeBetweenRounds) { this.timeBetweenRounds = timeBetweenRounds; }

    //Public getter methods
    public int getNrOfPlayers () { return nrOfPlayers; }
    public int getTimeBetweenRounds () { return timeBetweenRounds; }
    public List<PlayerHand> getHands() { return playerHandList; }
    public List<Card> getDealerCards() { return dealerHand.getCards(); }
    public boolean getShowSecond() { return showSecond;}
    public boolean activeGame() { return activeGame; }

    public boolean playerActionsNeeded() { return playerActionsNeeded; }

    public int getTimerCounter() { return timerCounter; }


    /*-----------------------
         Game functions
      -----------------------*/
    private void startGame() {
        dealer = new Dealer(7);
        dealerHand = new DealerHand();
        playerHandList = new ArrayList<>();
        for (int i = 0; i < nrOfPlayers; i++) { playerHandList.add(new PlayerHand()); }
        activeGame = playerActionsNeeded = showSecond = false;
        timerCounter = timeBetweenRounds;
    }

    /*
    Checks if a player with that name exists in the database.
    If not, a new player is created with 1000 credits, updated in the database, and added to MainModels "hands"-list.
    If the player already exists, it's credit score is recieved, and the player is added to the "hands"-list.
    */
    public void addPlayer (String name, int seat) {
        DatabaseHandler dbH = new DatabaseHandler();
        Player player = null;
        try {
            if (dbH.playerName(name)) {
                player = new Player(name, dbH.getCredits(name));
            } else {
                player = new Player(name, 1000);
                dbH.addPlayerData(player);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        setPlayerToHand(player, seat);
        updateObservers();
    }

    private void setPlayerToHand(Player player, int seat) { playerHandList.get(seat).setPlayer(player);}

    public void removePlayer(String name) { /* TODO*/ }

    public void playerBet (int seatNr, int bet) {
        playerHandList.get(seatNr).bet(bet);
        if (!activeGame) {
            activeGame = true;
            waitForBet();
        }
        updateObservers();
    }

    private void waitForBet() {
        timerCounter = timeBetweenRounds;
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerCounter > 0) {
                    timerCounter--;
                    updateObservers();
                    } else {
                    ((Timer)e.getSource()).stop();
                    newRound();
                }

            }
        }).start();
    }

    public void newRound() {
        showSecond = false;
        playerActionsNeeded = true;
        //currentHand = 0;
        for (int i = 0; i < 2; i++) {
            for (PlayerHand playerHand : playerHandList) {
                if (playerHand.getBet() > 0) {
                    playerHand.addCard(dealer.dealCard());
                }
            }
            dealerHand.addCard(dealer.dealCard());
        }
        updateObservers();
    }

    
    //Updates the database with the score of all players in the "hands"-list.
    public void dbUpdateScores () {
        DatabaseHandler dbH = new DatabaseHandler();
        for (PlayerHand hand : playerHandList) {
            try {
                dbH.addPlayerData(hand.getPlayer());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }





    public void playerHit(int seat) {
        //if (!activeGame) {return;}
        if (isBlackjack(playerHandList.get(seat))) { playerStand(seat); }
        playerHandList.get(seat).addCard(dealer.dealCard());
        int handValue = playerHandList.get(seat).getValue();
        if (handValue == 21) {
            playerStand(seat);
        } else if (handValue > 21) {
            playerStand(seat);
        }
        updateObservers();
    }


    public void playerStand(int seat) {
        if (!activeGame) {return;}
        if (seat == playerHandList.size()-1) { dealDealer(); }
        updateObservers();
    }

    public void playerDouble(int seat) {
        if (!activeGame) {return;}
        if (playerHandList.get(seat).getCards().size() != 2 ||
        isBlackjack(playerHandList.get(seat)) || playerHandList.get(seat).getValue() >= 21) {return;}
        playerHandList.get(seat).bet(playerHandList.get(seat).getBet());
        playerHandList.get(seat).addCard(dealer.dealCard());
        playerStand(seat);
    }
    public void playerSplit() {
        if (!activeGame) {return;}
        // göra här
    }



    private void dealDealer() {
        showSecond = true;
        new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dealerHand.getValue() < 17) {
                    dealerHand.addCard(dealer.dealCard());
                } else {
                    ((Timer)e.getSource()).stop();
                    payHands();
                }
                updateObservers();
            }
        }).start();
    }

    private boolean isBlackjack(PlayerHand hand) {return (hand.getValue() == 21) && (hand.getCards().size() == 2);}
    private void payHands() {
        int dealerValue = dealerHand.getValue();
        int handValue;
        for (PlayerHand h : playerHandList) {
            handValue = h.getValue();
            if (handValue > 21) {
                h.payout(0);
            } else if (isBlackjack(h)) {
                h.payout(2.5);
            } else if (dealerValue > 21) {
                h.payout(2);
            } else if (dealerValue > handValue) {
                h.payout(0);
            } else if (dealerValue < handValue) {
                h.payout(2);
            } else {
                h.payout(1);
            }
        }
        for (PlayerHand h : playerHandList) {
            h.clearHand();
        }
        dealerHand.clearHand();
        activeGame = false;
        //waitForBet();
    }

    /*-----------------------
         Observer functions
      -----------------------*/
    private  void updateObservers() {
        for (Observer<MainModel> o : observers) {
            o.update(this);
        }
    }
    @Override
    public void addObserver(Observer<MainModel> o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer<MainModel> o) {
        observers.remove(o);
    }
}
