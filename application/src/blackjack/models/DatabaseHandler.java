package blackjack.models;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DBMS, uses SQL queries fore communication
 * Do not forget to "Apply" jarfile!
 *
 * @author Tomas Alander & Tor Falkenberg
 */

public class DatabaseHandler {

    private final String url = "jdbc:mysql://85.10.205.173:3306/playerscore";
    private final String user = "tomasa";
    private final String password = "blackjack";
    private final String databaseName = "playerscore";
    private final String tableName = "Scoreboard";
    private final String kol1 = "name";
    private final String kol2 = "credits";

    private final String insertQuery = "INSERT INTO Scoreboard VALUES(?,?);";
    private final String getAll = "SELECT* FROM Scoreboard;";
    private final String getCredits = "SELECT credits FROM Scoreboard WHERE name = ";
    private final String addCredits = "UPDATE Scoreboard SET credits = ? WHERE name = ?";
    private final String deletePlayer = "DELETE FROM Scoreboard WHERE name = ?";
    private final String findPlayer = "SELECT name FROM Scoreboard WHERE name = ?";
    private final String sortPlayer = "SELECT* FROM Scoreboard ORDER BY credits DESC";

    public DatabaseHandler() {
    }

    /**
     * Creates an ArrayList of all players stored in the database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Player> getScoreBoard() throws SQLException, ClassNotFoundException {
        List<Player> playerList = new ArrayList<>();
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sortPlayer);
            while (rs.next()) {
                playerList.add(new Player(rs.getString(1), rs.getInt(2)));
            }
            rs.close();
            dbms.closeConnection(con, st);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return playerList;
    }
    }


    /**
     * returns a players credits
     * @param player
     * @return int
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int getCredits(String player) throws SQLException, ClassNotFoundException {
        int ret = -1;
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getCredits + "'" + player + "'" + ";");
            rs.next();                 // Låt stå!
            ret = rs.getInt(1);
            /*
            while (rs.next()) {
                String printName = rs.getString(1);
                ret = rs.getInt(2);
                System.out.println(printName);
            }
             */
            rs.close();
            dbms.closeConnection(con, st);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * add credits to a players bankroll, credits > 0
     * @param player
     * @param credits
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addCredits(String player, int credits) throws SQLException, ClassNotFoundException {
        int oldCredits;
        if (credits < 0) {
            System.out.println("credits must be greater than 0");
            return;
        }
        try {
            oldCredits = this.getCredits(player);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            PreparedStatement ps = con.prepareStatement(addCredits);
            ps.setInt(1, credits + oldCredits);
            ps.setString(2, player);
            ps.execute();
            System.out.println("PreparedStatement Insert  successful. name = " + player + ", credits added = " + credits);
            dbms.closeConnection(con, ps);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    /**
     * if player is in the database table Scoreboard return true
     * @param player
     * @return boolean
     * @throws SQLException, ClassNotFoundException
     */
    public boolean playerName(String player) throws SQLException, ClassNotFoundException {
        boolean ret = false;
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            PreparedStatement ps = con.prepareStatement(findPlayer);
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Where are no players named " + player + " in this table");
                rs.close();
                dbms.closeConnection(con, ps);
            } else {
                rs.close();
                dbms.closeConnection(con, ps);
                ret = true;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * remove player from the database table Scoreboard
     * @param player
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void removePlayer(String player) throws SQLException, ClassNotFoundException {
        int n = 0;
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            PreparedStatement ps = con.prepareStatement(deletePlayer);
            ps.setString(1, player);
            ps.execute();
            dbms.closeConnection(con, ps);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new player to the database table Scoreboard
     * @param player
     * @param credits
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addNewPlayer(String player, int credits) throws SQLException, ClassNotFoundException {
        if (credits < 0) {
            System.out.println("Credits cannot be negative!");
            return;
        }
        if (!this.playerName(player)) {     //Lös PRIMARY KEY för databasen!
            try {
                DBMSConnection dbms = new DBMSConnection(url, user, password);
                Connection con = dbms.connect();
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, player);
                ps.setInt(2, credits);
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player + ", credits = " + credits);
                dbms.closeConnection(con, ps);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("This player are already registered");
    }

    /**
     * @return a new HashMap<name,credits> of the database table Scoreboard
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public HashMap map() throws SQLException, ClassNotFoundException {
        HashMap<String, Integer> mapPlayers = new HashMap<>();
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAll);
            //rs.next();             //Behövs ej ty while!?
            while (rs.next()) {
                mapPlayers.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
            dbms.closeConnection(con, st);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mapPlayers;
    }

    /**
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Player player(String name) throws SQLException, ClassNotFoundException {
        if (playerName(name)) {
            try {
                DBMSConnection dbms = new DBMSConnection(url, user, password);
                Connection con = dbms.connect();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(getCredits + "'" + name + "'" + ";");
                rs.next();                 // Låt stå!
                int credits = rs.getInt(1);
                rs.close();
                dbms.closeConnection(con, st);
                return new Player(name, credits);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else System.out.println("No player with that name");
        return null;
    }

    /**
     * add a player to database or update players credits
     * @param player
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addPlayerData(Player player)throws SQLException, ClassNotFoundException {
        if (!playerName(player.getName())) {
            try{ DBMSConnection dbms = new DBMSConnection(url, user, password);
                Connection con = dbms.connect();
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, player.getName());
                ps.setInt(2, (int)player.getBalance());
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player.getName() + ", credits = " + player.getBalance());
                dbms.closeConnection(con, ps);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            try {
                DBMSConnection dbms = new DBMSConnection(url, user, password);
                Connection con = dbms.connect();
                PreparedStatement ps = con.prepareStatement(addCredits);
                ps.setInt(1, (int)player.getBalance());
                ps.setString(2, player.getName());
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player.getName() + ", credits added = " + player.getBalance());
                dbms.closeConnection(con, ps);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

/* // för testning
    public static void main(String[] args) throws Exception {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        HashMap<String, Integer> mapScoreboard = databaseHandler.map();
              mapScoreboard.forEach((k,v) -> {
              System.out.println("name: " + k + " credits: " + v);
              });
/*
            Player tomas = new Player("jon",333);
            try {
                databaseHandler.addPlayerData(tomas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
*/
}
