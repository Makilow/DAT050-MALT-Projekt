package blackjack.views;

import blackjack.models.MainModel;
import blackjack.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * MainView class, JFrame contains the visual interface,
 * all the panels; game, chat, menu, settings, scoreboard and rules
 * @author Lukas Wigren
 */
public class MainView extends JFrame implements Observer<MainModel> {
    private final JPanel container;
    private final CardLayout cl;
    private State currentState = State.MENU;
    private boolean isFullscreen = false;
    /**
     * Constructor for MainView
     * @param width screen width
     * @param height    screen height
     * @param container main panel, containing all other panels
     */
    public MainView(int width, int height, JPanel container) {
        this.container = container;
        this.cl = (CardLayout) container.getLayout();
        add(container);
        setTitle("Blackjack"); setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); pack(); setFocusable(true);
        setLocationRelativeTo(null); setVisible(true);
    }

    private void changeSize(int width, int height) {
        if (width == getWidth() && height == getHeight() || isFullscreen) return;
        setPreferredSize(new Dimension(width, height));
        pack();
    }

    private void toggleFullscreen(boolean f, int width, int height) {
        if (f == isFullscreen) return;
        isFullscreen^=true;
        dispose();
        if (isFullscreen) {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        } else {
            setPreferredSize(new Dimension(width, height));
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
    /**
     * Update functions, called by observer
     * updates the panel with newly updated mainModel
     * @param o MainModel
     */
    @Override
    public void update(MainModel o) {
        requestFocus();
        changeSize(o.getWidth(),o.getHeight());
        switchPanel(o.getState());
        toggleFullscreen(o.getIsFullscreen(), o.getWidth(), o.getHeight());
    }
}
