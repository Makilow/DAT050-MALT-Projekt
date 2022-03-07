package blackjack.models.tests;

import blackjack.models.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void initTest() {
        assertThrows(NullPointerException.class, () ->
        {
            new Player(null, 0);
        });
    }
    
    @Test
    void getBalance() {
        int r;
        Random rnd = new Random();
        Player player;
        for (int i = 0; i<10;i++) {
            r = rnd.nextInt();
            player = new Player("test", r);
            assertEquals(r, player.getBalance());
        }
    }

    @Test
    void changeBalance() {
        int r;
        double old_balance;
        Random rnd = new Random();
        Player player = new Player("test", 0);
        for (int i = 0; i<10;i++) {
            r = rnd.nextInt();
            old_balance = player.getBalance();
            player.changeBalance(r);
            assertEquals(r + old_balance, player.getBalance());
        }
    }

    @Test
    void getName() {
        Player player = new Player("Test", 0);
        assertEquals("Test", player.getName());
    }
}
