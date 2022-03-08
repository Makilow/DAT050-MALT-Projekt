package blackjack.models.tests;

import blackjack.Observer;
import blackjack.controllers.GameController;
import blackjack.models.MainModel;
import blackjack.models.Player;
import blackjack.models.PlayerHand;
import blackjack.views.GamePanel;
import blackjack.views.State;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainModelTest {
    MainModel mm = new MainModel();

    @Test
    void testGame() {
        GamePanel gp = new GamePanel(new GameController(mm), mm.getNrOfPlayers());
        mm.addObserver(gp);
        mm.setState(State.GAME);
        assertEquals(State.GAME, mm.getState());
        mm.addPlayer("Tor", 0);
        assertNotNull(mm.getHands());
        assertNotNull(mm.getDealerCards());
        mm.playerBet(0,200);
        mm.playerDouble(0);
        mm.playerHit(0);
        mm.playerStand(0);

        mm.removeObserver(gp);
    }

    @Test
    void setSize() {
        mm.setSize(1080, 720);
        assertEquals(1080, mm.getWidth());
        assertEquals(720, mm.getHeight());
    }

    @Test
    void getIsFullscreen() {
        assertFalse(mm.getIsFullscreen());
        mm.toggleFullscreen();
        assertTrue(mm.getIsFullscreen());
    }

    @Test
    void getNrOfPlayers() {
        assertEquals(5, mm.getNrOfPlayers());
    }

    @Test
    void getShowSecond() {
        assertFalse(mm.getShowSecond());
    }

    @Test
    void activeGame() {
        assertFalse(mm.activeGame());
    }

    @Test
    void playerActionsNeeded() {
        assertFalse(mm.playerActionsNeeded());
    }

    @Test
    void getTimerCounter() {
        assertEquals(0, mm.getTimerCounter());
    }

    @Test
    void playSE() {
    }

    @Test
    void getSoundON() {
        assertFalse(mm.getSoundON());
        mm.toggleSound();
        assertTrue(mm.getSoundON());
        mm.switchSong(mm.currentSong());
    }
}
