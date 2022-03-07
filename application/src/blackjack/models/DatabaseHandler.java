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
 * @version 2022-03-07
 */

public class DatabaseHandler {

    private String url = "jdbc:mysql://85.10.205.173:3306/playerscore";
    private String user = "tomasa";
    private String password = "blackjack";
    private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private final String insertQuery = "INSERT INTO Scoreboard VALUES(?,?);";
    private final String getAllQuery = "SELECT* FROM Scoreboard;";
    private final String getCreditsQuery = "SELECT credits FROM Scoreboard WHERE name = ";
    private final String addCreditsQuery = "UPDATE Scoreboard SET credits = ? WHERE name = ?";
    private final String deletePlayerQuery = "DELETE FROM Scoreboard WHERE name = ?";
    private final String findPlayerQuery = "SELECT name FROM Scoreboard WHERE name = ?";
    private final String sortPlayerQuery = "SELECT* FROM Scoreboard ORDER BY credits DESC";

    /**
     * A constructor for class DatabaseHandler
     */
    public DatabaseHandler() {}

    /**
     * Constructor mainly fore JUnit test purpose.
     * @param user
     * username to your local database
     * @param password
     * password to your local database
     */
    public DatabaseHandler(String user,String password) {
        this.user = user;
        this.password = password;
        this.url = "jdbc:h2:~/test";
        this.jdbcDriver = "org.h2.Driver";
    }

    /**
     * Creates a sorted ArrayList of all players stored in the database
     *
     * @return  An ArrayList of Players
     */
    public List<Player> getScoreBoard(){
        List<Player> playerList = new ArrayList<>();
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
             Connection con = dbms.connect();){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sortPlayerQuery);
            while (rs.next()) {
                playerList.add(new Player(rs.getString(1), rs.getInt(2)));
            }
            rs.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        return playerList;
    }

    /**
     * returns a players credits
     *
     * @param player
     * A String players name
     * @return int
     * A players credit given the players name
     */
    public int getCredits(String player){
        int ret = -1;
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
            Connection con = dbms.connect();){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getCreditsQuery + "'" + player + "'" + ";");
            rs.next();                 // Låt stå!
            ret = rs.getInt(1);
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    /**
     * if player is in the database table Scoreboard return true
     *
     * @param player
     * A String players name
     * @return boolean
     */
    public boolean playerName(String player){
        boolean ret = false;
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
            Connection con = dbms.connect();){
            PreparedStatement ps = con.prepareStatement(findPlayerQuery);
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Where are no player named " + player + " in this table");
                rs.close();
            } else {
                rs.close();
                ret = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    /**
     * add credits to a players bankroll, credits > 0
     *
     * @param player
     * A players name
     * @param credits
     * A players credits
     */
    public void addCredits(String player, int credits){
        int oldCredits;
        if (credits <= 0) {
            System.out.println("credits must be greater than 0");
            return;
        }
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
             Connection con = dbms.connect();){
            if(getPlayerName(player,con)){
                oldCredits = this.getPlayerCredits(player,con);
                PreparedStatement ps = con.prepareStatement(addCreditsQuery);
                ps.setInt(1, credits + oldCredits);
                ps.setString(2, player);
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player + ", credits added = " + credits);
            }else System.out.println("Where are no players named " + player + " in this table");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * remove player from the database table Scoreboard
     *
     * @param player
     * Player name
     */
    public void removePlayer(String player) {
        int n = 0;
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
            Connection con = dbms.connect();){
            PreparedStatement ps = con.prepareStatement(deletePlayerQuery);
            ps.setString(1, player);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Where are no players to delete named " + player + " in this table");
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new player to the database table Scoreboard
     *
     * @param player
     * A Sting player name
     * @param credits
     * A players credits
     */
    public void addNewPlayer(String player, int credits) {
        if (credits < 0) {
            System.out.println("Credits cannot be negative!");
            return;
        }
            try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
                Connection con = dbms.connect();){
                if (!this.getPlayerName(player,con)) {
                    PreparedStatement ps = con.prepareStatement(insertQuery);
                    ps.setString(1, player);
                    ps.setInt(2, credits);
                    ps.execute();
                    System.out.println("PreparedStatement Insert  successful. name = " + player + ", credits = " + credits);
                }else System.out.println("Player " +player+ " are already registered");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * @return a new HashMap<name,credits> of the database table Scoreboard
     */
    public HashMap map() {
        HashMap<String, Integer> mapPlayers = new HashMap<>();
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, "",this.jdbcDriver);
            Connection con = dbms.connect();){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAllQuery);
            while (rs.next()) {
                mapPlayers.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mapPlayers;
    }

    /**
     * Returns a new player if the players name is in the database
     * @param name
     * A Sting of a players name
     */
    public Player player(String name) {
        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
            Connection con = dbms.connect();){
            if (getPlayerName(name,con)) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(getCreditsQuery + "'" + name + "'" + ";");
                rs.next();                 // Låt stå!
                int credits = rs.getInt(1);
                rs.close();
                return new Player(name, credits);
            } else System.out.println("No player with that name");
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * if player is not in the database add player to database else update player credit balance
     *
     * @param player
     * an object Player
     */
    public void addPlayerData(Player player){

        try (DBMSConnection dbms = new DBMSConnection(this.url, this.user, this.password, this.jdbcDriver);
            Connection con = dbms.connect();){
            if (!getPlayerName(player.getName(), con)) {
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.setString(1, player.getName());
                ps.setInt(2, (int) player.getBalance());
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player.getName() + ", credits = " + player.getBalance());
            } else {
                PreparedStatement ps = con.prepareStatement(addCreditsQuery);
                ps.setInt(1, (int) player.getBalance());
                ps.setString(2, player.getName());
                ps.execute();
                System.out.println("PreparedStatement Insert  successful. name = " + player.getName() + ", credits added = " + player.getBalance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean getPlayerName(String player,Connection con){
        boolean ret = false;
        try {
            PreparedStatement ps = con.prepareStatement(findPlayerQuery);
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Where are no player named " + player + " in this table");
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

    private int getPlayerCredits(String player,Connection con){
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

    private class DBMSConnection implements AutoCloseable{
        private String url;
        private String user;
        private String password;
        private String jdbcDriver;

        public DBMSConnection(String url, String user, String password, String jdbcDriver) {
            this.url = url;
            this.user = user;
            this.password = password;
            this.jdbcDriver = jdbcDriver;
        }

        /**
         * Establish  a connection
         *
         * @return new Connection
         * @throws ClassNotFoundException
         * @throws SQLException
         */
        public Connection connect() throws ClassNotFoundException, SQLException {
            Class.forName(this.jdbcDriver);
            Connection con = DriverManager.getConnection(this.url, this.user, this.password);
           // System.out.println("Connection established");
            return con;
        }

        @Override
        public void close() throws Exception {
           // System.out.println("Connection closet");
        }
    }
}
