package blackjack.models.tests;

import blackjack.models.DatabaseHandler;
import blackjack.models.Player;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

/**
 * Test DatabaseHandler, using an In-memory database
 * @author Tomas Alander
 * @version 2022-03-07
 */
class JUnitTestDBHandler{
    private DatabaseHandler db;
    private Statement stmt;
    private Connection conn;
    private String sqlQuery;
    @BeforeAll
    public static void startTest() {
        System.out.println("Start DatabaseHandler test");
    }
    @BeforeEach
    void setUp() throws SQLException {
        db = new DatabaseHandler("sa","");
        String sqlQuery;
        try {
            Class.forName("org.h2.Driver");
            conn = (DriverManager.getConnection("jdbc:h2:~/test","sa",""));
            stmt = conn.createStatement();

            sqlQuery = "DELETE Scoreboard ";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Scoreboard " + "VALUES ('Tomas',1000)";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Scoreboard " + "VALUES ('Mark',900)";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Scoreboard " + "VALUES ('Arvin',500)";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Scoreboard " + "VALUES('Lukas',600)";
            stmt.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO Scoreboard " + "VALUES('Tor',700)";
            stmt.executeUpdate(sqlQuery);

        }catch(Exception e) {
            throw  new RuntimeException(e);
        }
        finally {
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        }
    }
    @Test
    @DisplayName("Test playerName function")
    public void testPlayerName(){
        assertEquals(false,db.playerName("Pelle"));
        assertEquals(true,db.playerName("Tor"));
        assertEquals(false,db.playerName("tomas"));
    }
    @Test
    @DisplayName("Test getScoreBoard function")
    public void testGetScoreBoard(){
        assertEquals(5,db.getScoreBoard().size());
        assertEquals("Lukas", db.getScoreBoard().get(3).getName());
        assertEquals("Mark", db.getScoreBoard().get(1).getName());
        assertEquals(1000, (int)db.getScoreBoard().get(0).getBalance());
        assertEquals(500, (int)db.getScoreBoard().get(4).getBalance());
    }

    @Test
    @DisplayName("Test addPlayerData function")
    public void testAddPlayerData(){
        db.addPlayerData(new Player("Pelle",333));
        db.addPlayerData(new Player("Tor",876));
        assertEquals(true, db.playerName("Tor"));
        assertEquals(876, db.getCredits("Tor"));
        assertEquals(true, db.playerName("Pelle"));
        assertEquals(true, db.playerName("Pelle"));
        assertEquals(false, db.playerName("pelle"));
        assertEquals(333, db.getCredits("Pelle"));
        assertEquals(false, 321 == db.getCredits("Pelle"));
    }

    @Test
    @DisplayName("Test  getCredits function")
    public void testGetCredits() {

        assertEquals(1000, db.getCredits("Tomas"));
        assertEquals(700, db.getCredits("Tor"));
        Assertions.assertNotEquals(333, db.getCredits("Mark"));
        Assertions.assertNotEquals(123, db.getCredits("Arvin"));
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("All DatabaseHandler tests are completed");
    }
}
