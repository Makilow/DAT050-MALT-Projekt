package blackjack.models;

import blackjack.Observable;
import blackjack.Observer;
import blackjack.views.State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.*;
import javax.swing.Timer;

/**
 * MainModel, represents a Model as per MVC-design
 * @author Lukas Wigren, Tomas Alander, Tor Falkenberg, Mark Villarosa
 */
public class MainModel implements Observable<MainModel> {

    //Local Setting Variables
    private int width=1280, height=720;
    private State state;
    private final Collection<Observer<MainModel>> observers = new HashSet<>();
    private boolean isFullscreen = false;
    private boolean soundON = false;
    private int currentSong;
    private final Sounds sound = new Sounds();
    private int nrOfPlayers = 5;            //Default number of players = 5
    private int timeBetweenRounds = 30;     //Default number of seconds between rounds

    //Local Game Variables
    private Dealer dealer;
    private DealerHand dealerHand;
    private List<PlayerHand> playerHandList;
    private boolean activeGame, playerActionsNeeded, showSecond;
    private int timerCounter;

    // chat
    ChatClient chatClient;
    private String username;
    ArrayList<String> messages = new ArrayList<>();
    
    //Constructor
    public MainModel() { state = State.MENU; }

    // Screen methods
    public void setSize(int width, int height) {this.width=width; this.height=height; updateObservers();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public boolean getIsFullscreen() { return isFullscreen; }
    public void toggleFullscreen() { isFullscreen ^= true; updateObservers(); }

    //Setting methods
    public void setState(State state) {
        if (state == State.GAME) {startGame();}
        this.state = state;
        updateObservers();
    }
    public State getState() { return state; }
    public void setNrOfPlayers (int nrOfPlayers) { this.nrOfPlayers = nrOfPlayers; }
    public void setTimeBetweenRounds (int timeBetweenRounds) { this.timeBetweenRounds = timeBetweenRounds; }

    //Sound methods
    public boolean getSoundON() {
        return soundON;
    }
    public int currentSong() {return currentSong;}
    public void toggleSound() {
        soundON ^= true;
        if (soundON) {
            sound.setFile(currentSong);
            sound.play();
            sound.loop();
        } else sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
    public void switchSong(int song) {
        sound.stop();
        currentSong = song;
        sound.setFile(currentSong);
        if (soundON) {
            sound.play();
        }
    }
    
    //Public getter methods
    public int getNrOfPlayers () { return nrOfPlayers; }
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
        if (dbH.playerName(name)) {
            player = new Player(name, dbH.getCredits(name));
        } else {
            player = new Player(name, 1000);
            dbH.addPlayerData(player);
        }
        setPlayerToHand(player, seat);
        updateObservers();
    }

    private void setPlayerToHand(Player player, int seat) { playerHandList.get(seat).setPlayer(player);}

    public void removePlayer(int seat) {
        playerHandList.get(seat).removePlayer();
        updateObservers();
    }

    public void playerBet (int seatNr, int bet) {
        playerHandList.get(seatNr).bet(bet);
        if (!activeGame) {
            activeGame = true;
            waitForBet();
        }
        updateObservers();
    }

    private void waitForBet() {
        if (activeGame) {
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
    }

    public void newRound() {
        showSecond = false;
        playerActionsNeeded = true;
        for (int i = 0; i < 2; i++) {
            for (PlayerHand playerHand : playerHandList) {
                if (playerHand.getBet() > 0) {
                    playerHand.addCard(dealer.dealCard());
                    if (i == 1) {
                        playerHand.setActionDone(false);
                    }
                }
            }
            dealerHand.addCard(dealer.dealCard());
        }
        updateObservers();
    }

    private boolean isBlackjack(PlayerHand hand) {return (hand.getValue() == 21) && (hand.getCards().size() == 2);}
    
    public void playerHit(int seat) {
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
        playerHandList.get(seat).setActionDone(true);
        int done = 0;
        for (PlayerHand hand : playerHandList) {
            if (hand.isActionDone()) { done++; }
        }
        if (done == nrOfPlayers) { dealDealer(); }
        updateObservers();
    }

    public void playerDouble(int seat) {
        if (playerHandList.get(seat).getCards().size() != 2 ||
        isBlackjack(playerHandList.get(seat)) || playerHandList.get(seat).getValue() >= 21) {return;}
        playerHandList.get(seat).bet(playerHandList.get(seat).getBet());
        playerHandList.get(seat).addCard(dealer.dealCard());
        playerStand(seat);
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
        dealerHand.clearHand();
        activeGame = false;
        playerActionsNeeded = false;
        dbUpdateScores();
        waitForBet();
    }

    //Updates the database with the score of all players in the "hands"-list.
    public void dbUpdateScores () {
        DatabaseHandler dbH = new DatabaseHandler();
        for (PlayerHand hand : playerHandList) {
            if (hand.getPlayer() != null) { dbH.addPlayerData(hand.getPlayer()); }

        }
    }
    
    /*-----------------------
        Chat functions
      -----------------------*/

    public void setChatUsername(String uname) {
        username = uname;
    }
    private void startChat() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
        chatClient = new ChatClient(socket, username, this);
        addToMessages("Welcome " + username + "!");
        chatClient.listenForMessage();
    }
    public void sendMessage(String message) {
        chatClient.sendMessage(message);
        addToMessages("Me: "+message);
    }
    public ArrayList<String> getMessages() {
        return messages;
    }
    public void addToMessages(String message) {
        messages.add(0,message);
        if (messages.size() > 50) {
            messages.remove(50);
        }
        updateObservers();
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
