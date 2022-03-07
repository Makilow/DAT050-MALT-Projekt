package blackjack.models.tests;

import blackjack.models.MainModel;
import blackjack.views.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainModelTest {

    @Test
    void setSize() {
        MainModel mm = new MainModel();
        mm.setSize(1080, 720);
        assertEquals(1080, mm.getWidth());
        assertEquals(720, mm.getHeight());
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void setState() {
        MainModel mm = new MainModel();
        mm.setState(State.CHAT);
        assertEquals(State.CHAT, mm.getState());
    }

    @Test
    void getState() {
    }

    @Test
    void getIsFullscreen() {
    }

    @Test
    void toggleFullscreen() {
    }

    @Test
    void setNrOfPlayers() {
    }

    @Test
    void setTimeBetweenRounds() {
    }

    @Test
    void getNrOfPlayers() {
    }

    @Test
    void getTimeBetweenRounds() {
    }

    @Test
    void getHands() {
    }

    @Test
    void getDealerCards() {
    }

    @Test
    void getShowSecond() {
    }

    @Test
    void activeGame() {
    }

    @Test
    void playerActionsNeeded() {
    }

    @Test
    void getTimerCounter() {
    }

    @Test
    void playSE() {
    }

    @Test
    void getSoundON() {
    }

    @Test
    void toggleSound() {
    }

    @Test
    void switchSong() {
    }

    @Test
    void currentSong() {
    }

    @Test
    void addPlayer() {
    }

    @Test
    void removePlayer() {
    }

    @Test
    void playerBet() {
    }

    @Test
    void newRound() {
    }

    @Test
    void dbUpdateScores() {
    }

    @Test
    void playerHit() {
    }

    @Test
    void playerStand() {
    }

    @Test
    void playerDouble() {
    }

    @Test
    void playerSplit() {
    }

    @Test
    void addObserver() {
    }

    @Test
    void removeObserver() {
    }
}
