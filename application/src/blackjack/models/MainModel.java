package blackjack.models;

import blackjack.Observable;
import blackjack.Observer;
import blackjack.views.State;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;

/**
 * MainModel, represents a Model as per MVC-design
 * @author Lukas Wigren, Tomas Alander, Tor Falkenberg
 */
public class MainModel implements Observable<MainModel> {
    private int width=1200, height=800;
    private State state;
    private final Collection<Observer<MainModel>> observers = new HashSet<>();
    private boolean isFullscreen = false;

    private final DatabaseHandler dbHandler = new DatabaseHandler();
    private List<Player> playerList;

    private Dealer dealer;
    private List<PlayerHand> playerHands;
    private DealerHand dealerHand;
    private int currentHand;
    private boolean showSecond;


    public MainModel() {
        state = State.MENU;
        try {
            playerList = dbHandler.getAllPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Screen functions
    public void setSize(int width, int height) {this.width=width; this.height=height; updateObservers();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void setState(State state) {
        if (state == State.NEWGAME) {startGame();}
        if (state == State.STARTGAME) {newRound();}
        this.state = state;
        updateObservers();
    }
    public State getState() { return state; }
    public boolean getIsFullscreen() {return isFullscreen;}
    public void toggleFullscreen() {isFullscreen ^= true; updateObservers();}
    public List<Player> getPlayerList() { return playerList;}


    // Game functions
    private void startGame() {
        dealer = new Dealer(7);
        playerHands = new LinkedList<>();
        dealerHand = new DealerHand();
        currentHand = 0;
    }

    public void newPlayer() {
        String name = JOptionPane.showInputDialog("Name:");
        addHand(new Player(name, 1000));
    }

    public void addHand(Player player) {
        playerHands.add(new PlayerHand(player));
        updateObservers();
    }

    public List<PlayerHand> getHands() { return playerHands; }

    public List getDealerCards() {return dealerHand.getCards();}

    public void newRound() {
        showSecond = false;
        currentHand = 0;
        for (int i = 0; i < 2; i++) {
            for (PlayerHand h : playerHands) {
                //if (h.getBet() == 0) {continue;}
                h.addCard(dealer.dealCard());
            }
            dealerHand.addCard(dealer.dealCard());
        }
        updateObservers();
    }

    public boolean getShowSecond() { return showSecond;}

    public void update() {updateObservers();}

    public void playerHit() {
        if (isBlackjack(playerHands.get(currentHand))) { playerStand(); }
        playerHands.get(currentHand).addCard(dealer.dealCard());
        int handValue = playerHands.get(currentHand).getValue();
        if (handValue == 21) {
            playerStand();
        } else if (handValue > 21) {
            playerStand();
        }
        updateObservers();
    }
    public void playerStand() {
        if (currentHand == playerHands.size()-1) {
            dealDealer();
        } else {
            currentHand++;
        }
        updateObservers();
    }
    public void playerDouble() {
        if (playerHands.get(currentHand).getCards().size() != 2 ||
        isBlackjack(playerHands.get(currentHand)) ||
                playerHands.get(currentHand).getValue() >= 21) {return;}
        playerHands.get(currentHand).bet(playerHands.get(currentHand).getBet());
        playerHands.get(currentHand).addCard(dealer.dealCard());
        playerStand();
    }
    public void playerSplit() {
        // göra här
    }

    private void dealDealer() {
        showSecond = true;
        while (dealerHand.getValue() < 17) {
            dealerHand.addCard(dealer.dealCard());
            updateObservers();
        }
        payHands();
    }

    private boolean isBlackjack(PlayerHand hand) {return (hand.getValue() == 21) && (hand.getCards().size() == 2);}
    private void payHands() {
        int dealerValue = dealerHand.getValue();
        int handValue;
        for (PlayerHand h : playerHands) {
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
        for (PlayerHand h : playerHands) {
            h.clearHand();
        }
        dealerHand.clearHand();
        currentHand = 0;
        newRound();
    }
    // Observer functions
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
