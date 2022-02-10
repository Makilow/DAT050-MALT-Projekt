package blackjack.views;

import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * MainView class, JFrame contains the visual interface
 * @author Lukas Wigren
 */
public class MainView extends JFrame implements Observer<MainModel> {
    private final JPanel container;
    private final CardLayout cl;
    private State currentState = State.MENU;
    private boolean isFullscreen = false;

    public MainView(String title, int width, int height, JPanel container) {
        this.container = container;
        this.cl = (CardLayout) container.getLayout();
        add(container);
        setTitle(title); setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); pack(); setFocusable(true);
        setLocationRelativeTo(null); setVisible(true);
    }

    private void changeSize(int width, int height) {
        if (width == getWidth() && height == getHeight()) return;
        setPreferredSize(new Dimension(width, height));
        pack();
    }

    private void toggleFullscreen(boolean f) {
        if (f == isFullscreen) return;
        isFullscreen^=true;
        dispose();
        if (isFullscreen) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        } else {
            setPreferredSize(new Dimension(500, 500));
            setUndecorated(false); pack();
            setLocationRelativeTo(null);
        }
        setVisible(true);
    }
    private void switchPanel(State state) {
        if (state == State.EXIT)  System.exit(0);
        if (state == currentState) return;
        currentState = state;
        cl.show(container, state.toString());
    }

    @Override
    public void update(MainModel o) {
        changeSize(o.getWidth(),o.getHeight());
        switchPanel(o.getState());
        toggleFullscreen(o.getIsFullscreen());
    }
}
