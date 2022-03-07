package blackjack.models.tests;

import blackjack.models.Player;
import blackjack.models.PlayerHand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerHandTest {

    @Test
    void setPlayer() {
        Player testPlayer = new Player("testPlayer", 1000);
        PlayerHand pH = new PlayerHand();
        pH.setPlayer(testPlayer);
        assertEquals(pH.getPlayer(), testPlayer);
    }

    @Test
    void getBet() {
        PlayerHand pH = new PlayerHand();
        assertEquals(0, pH.getBet());

        pH.setPlayer(new Player("testPlayer", 1000));
        pH.bet(200);
        assertEquals(200, pH.getBet());

        pH.clearHand();
        pH.bet(-200);
        assertEquals(0, pH.getBet());
    }

    @Test
    void getPlayer() {
        Player testPlayer = new Player("testPlayer", 1000);
        PlayerHand pH = new PlayerHand();
        pH.setPlayer(testPlayer);
        assertEquals(pH.getPlayer(), testPlayer);
    }

    @Test
    void removePlayer() {
        PlayerHand pH = new PlayerHand(new Player("testPlayer", 1000));
        pH.removePlayer();
        assertNull(pH.getPlayer());
    }

    @Test
    void clearHand() {
        PlayerHand pH = new PlayerHand(new Player("testPlayer", 1000));
        pH.setActionDone(false);
        pH.bet(200);
        pH.clearHand();
        assertTrue(pH.isActionDone());
        assertEquals(0, pH.getBet());
        assertTrue(pH.getCards().isEmpty());
    }

    @Test
    void bet() {
        PlayerHand pH = new PlayerHand(new Player("testPlayer", 1000));
        pH.bet(200);
        assertEquals(200, pH.getBet());
    }

    @Test
    void payout() {
        PlayerHand pH = new PlayerHand(new Player("testPlayer", 1000));
        pH.bet(200);
        pH.payout(2);
        assertTrue(pH.isActionDone());
        assertEquals(0, pH.getBet());
        assertTrue(pH.getCards().isEmpty());
        assertEquals(1200, pH.getPlayer().getBalance());
    }
}
