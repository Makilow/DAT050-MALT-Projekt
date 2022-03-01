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
import java.util.LinkedList;
import javax.swing.Timer;

/**
 * MainModel, represents a Model as per MVC-design
 * @author Lukas Wigren, Tomas Alander, Tor Falkenberg
 */
public class MainModel implements Observable<MainModel> {
    private int width=800, height=600;
    private State state;
    private final Collection<Observer<MainModel>> observers = new HashSet<>();
    private boolean isFullscreen = false;
    private Dealer dealer;
    private List<PlayerHand> hands;
    private DealerHand dealerHand;
    private int currentHand;
    private boolean showSecond;


    public MainModel() {
        state = State.MENU;
    }
    // Screen functions
    public void setSize(int width, int height) {this.width=width; this.height=height; updateObservers();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void setState(State state) {
        if (state == State.GAME) {startGame();}
        this.state = state;
        updateObservers();
    }
    public State getState() { return state; }
    public boolean getIsFullscreen() {return isFullscreen;}
    public void toggleFullscreen() {isFullscreen ^= true; updateObservers();}
    

    // Game functions
    private void startGame() {
        dealer = new Dealer(7);
        hands = new LinkedList<>();
        dealerHand = new DealerHand();
        currentHand = 0;
        addHand(new Player("Lukas", 1000));
        addHand(new Player("Tomas", 1000));
        addHand(new Player("Tomas", 1000));
        addHand(new Player("Tomas", 1000));
        addHand(new Player("Tomas", 1000));
        newRound();
    }
    public void addHand(Player player) { hands.add(new PlayerHand(player));}
    public List getHands() { return hands; }
    public List getDealerCards() {return dealerHand.getCards();}
    public void newRound() {
        showSecond = false;
        currentHand = 0;
        for (int i = 0; i < 2; i++) {
            for (PlayerHand h : hands) {
                //if (h.getBet() == 0) {continue;}
                h.addCard(dealer.dealCard());
            }
            dealerHand.addCard(dealer.dealCard());
        }
        updateObservers();
    }

    public boolean getShowSecond() { return showSecond;}

    public void playerHit() {
        if (isBlackjack(hands.get(currentHand))) { playerStand(); }
        hands.get(currentHand).addCard(dealer.dealCard());
        int handValue = hands.get(currentHand).getValue();
        if (handValue == 21) {
            playerStand();
        } else if (handValue > 21) {
            playerStand();
        }
        updateObservers();
    }
    public void playerStand() {
        if (currentHand == hands.size()-1) {
            dealDealer();
        } else {
            currentHand++;
        }
        updateObservers();
    }
    public void playerDouble() {
        if (hands.get(currentHand).getCards().size() != 2 ||
        isBlackjack(hands.get(currentHand)) ||
        hands.get(currentHand).getValue() >= 21) {return;}
        hands.get(currentHand).bet(hands.get(currentHand).getBet());
        hands.get(currentHand).addCard(dealer.dealCard());
        playerStand();
    }
    public void playerSplit() {
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
        for (PlayerHand h : hands) {
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
        for (PlayerHand h : hands) {
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
