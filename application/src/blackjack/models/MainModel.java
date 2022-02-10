package blackjack.models;

import blackjack.Observable;
import blackjack.Observer;
import blackjack.views.State;
import java.util.Collection;
import java.util.HashSet;
/**
 * MainModel, represents a Model as per MVC-design
 * @author Lukas Wigren
 */
public class MainModel implements Observable<MainModel> {
    private final String title = "blackjack";
    private int width=800, height=600;
    private State state;
    private final Collection<Observer<MainModel>> observers;
    private boolean isFullscreen = false;

    public MainModel() {
        state = State.MENU;
        observers = new HashSet<>();
    }
    public String getTitle() {return title;}
    public void setSize(int width, int height) {this.width=width; this.height=height; updateObservers();}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void setState(State state) {
        this.state = state;
        updateObservers();
    }
    public State getState() { return state; }
    public boolean getIsFullscreen() {return isFullscreen;}
    public void toggleFullscreen() {isFullscreen ^= true; updateObservers();}

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
