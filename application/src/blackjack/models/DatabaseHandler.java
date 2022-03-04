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
    private final String getAllQuery = "SELECT* FROM Scoreboard;";
    private final String getCreditsQuery = "SELECT credits FROM Scoreboard WHERE name = ";
    private final String addCreditsQuery = "UPDATE Scoreboard SET credits = ? WHERE name = ?";
    private final String deletePlayerQuery = "DELETE FROM Scoreboard WHERE name = ?";
    private final String findPlayerQuery = "SELECT name FROM Scoreboard WHERE name = ?";
    private final String sortPlayerQuery = "SELECT* FROM Scoreboard ORDER BY credits DESC";

    public DatabaseHandler() {}

    /**
     * Creates an ArrayList of all players stored in the database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Player> getScoreBoard() throws SQLException, ClassNotFoundException {
        List<Player> playerList = new ArrayList<>();
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sortPlayerQuery);
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

    /**
     * returns a players credits
     *
     * @param player
     * @return int
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    //=================Redundant function?================
    public int getCredits(String player) throws SQLException, ClassNotFoundException {
        int ret = -1;
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getCreditsQuery + "'" + player + "'" + ";");
            rs.next();                 // Låt stå!
            ret = rs.getInt(1);
            rs.close();
            dbms.closeConnection(con, st);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * add credits to a players bankroll, credits > 0
     *
     * @param player
     * @param credits
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addCredits(String player, int credits) throws SQLException, ClassNotFoundException {
        int oldCredits;
        if (credits <= 0) {
            System.out.println("credits must be greater than 0");
            return;
        }
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            if(getPlayerName(player,con)){
            oldCredits = this.getPlayerCredits(player,con);
            PreparedStatement ps = con.prepareStatement(addCreditsQuery);
            ps.setInt(1, credits + oldCredits);
            ps.setString(2, player);
            ps.execute();
            System.out.println("PreparedStatement Insert  successful. name = " + player + ", credits added = " + credits);
            dbms.closeConnection(con, ps);
            }else System.out.println("Where are no players named " + player + " in this table");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * if player is in the database table Scoreboard return true
     *
     * @param player
     * @return boolean
     * @throws SQLException, ClassNotFoundException
     */
    public boolean playerName(String player) throws SQLException, ClassNotFoundException {
        boolean ret = false;
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            PreparedStatement ps = con.prepareStatement(findPlayerQuery);
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * remove player from the database table Scoreboard
     *
     * @param player
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void removePlayer(String player) throws SQLException, ClassNotFoundException {
        int n = 0;
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            PreparedStatement ps = con.prepareStatement(deletePlayerQuery);
            ps.setString(1, player);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Where are no players to delete named " + player + " in this table");
            }
            rs.close();
            dbms.closeConnection(con, ps);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new player to the database table Scoreboard
     *
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
            try {
                DBMSConnection dbms = new DBMSConnection(url, user, password);
                Connection con = dbms.connect();
                if (!this.getPlayerName(player,con)) {
                    PreparedStatement ps = con.prepareStatement(insertQuery);
                    ps.setString(1, player);
                    ps.setInt(2, credits);
                    ps.execute();
                    System.out.println("PreparedStatement Insert  successful. name = " + player + ", credits = " + credits);
                    dbms.closeConnection(con, ps);
                }else System.out.println("Player " +player+ " are already registered");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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
            ResultSet rs = st.executeQuery(getAllQuery);
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
     * Returns a new player if the players name is in the database
     * @param name
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Player player(String name) throws SQLException, ClassNotFoundException {
        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            if (getPlayerName(name,con)) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(getCreditsQuery + "'" + name + "'" + ";");
                rs.next();                 // Låt stå!
                int credits = rs.getInt(1);
                rs.close();
                dbms.closeConnection(con, st);
                return new Player(name, credits);
            } else System.out.println("No player with that name");
            return null;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * if player is not in the database add player to database else update player credit balanse
     *
     * @param player
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addPlayerData(Player player) throws SQLException, ClassNotFoundException {

        try {
            DBMSConnection dbms = new DBMSConnection(url, user, password);
            Connection con = dbms.connect();
            if (!getPlayerName(player.getName(), con)) {
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, player.getName());
                ps.setInt(2, (int) player.getBalance());
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player.getName() + ", credits = " + player.getBalance());
                dbms.closeConnection(con, ps);
            } else {
                PreparedStatement ps = con.prepareStatement(addCreditsQuery);
                ps.setInt(1, (int) player.getBalance());
                ps.setString(2, player.getName());
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player.getName() + ", credits added = " + player.getBalance());
                dbms.closeConnection(con, ps);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean getPlayerName(String player,Connection con) throws SQLException{
        boolean ret = false;
        try {
            PreparedStatement ps = con.prepareStatement(findPlayerQuery);
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Where are no players named " + player + " in this table");
                rs.close();
            } else {
                rs.close();
                ret = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private int getPlayerCredits(String player,Connection con) throws SQLException{
        int ret = 0;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getCreditsQuery + "'" + player + "'" + ";");
            rs.next();                 // Låt stå!
            ret = rs.getInt(1);
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

 //================For test purpose========================
    /*
    public static void main(String[] args) throws Exception {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        HashMap<String, Integer> mapScoreboard = databaseHandler.map();
        mapScoreboard.forEach((k, v) -> {
            System.out.println("name: " + k + " credits: " + v);
        });
        //      databaseHandler.addNewPlayer("Jonas",111);
        // List<Player>player = databaseHandler.getScoreBoard();
        //Player jon = new Player("jon",333);
        //databaseHandler.addPlayerData(jon);
        //Player Karin = databaseHandler.player("Karin");
        // databaseHandler.addCredits("Tomas",333);
        // databaseHandler.addCredits("Karin",123);
    }

     */
}
