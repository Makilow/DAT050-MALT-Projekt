package blackjack.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Establish a new  DBMS connection using JDBC and MySql.
 * Do not forget to "Apply" jarfile!
 *
 * @author Tomas Alander
 */
public class DBMSConnection {
    private String url;
    private String user;
    private String password;
    private Connection con;

    public DBMSConnection() {}

    public DBMSConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Establish  a connection
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(this.url, this.user, this.password);
        System.out.println("Connection established");
        return con;
    }

    /**
     * Close connection
     * @param dbms
     * @param st
     * @throws SQLException
     */
    public void closeConnection(Connection dbms, Statement st) throws SQLException {
        st.close();
        dbms.close();
    }
}

