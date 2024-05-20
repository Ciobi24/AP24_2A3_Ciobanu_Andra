package DataBase;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.*;

public class Database {

    private static final String URL =
            "jdbc:mysql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "andraciobanu24";
    private static Connection connection = null;

    private static HikariDataSource hikariDataSource;


    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setUsername(USER);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.setMaximumPoolSize(15);
        hikariConfig.setAutoCommit(false); // Dezactivează autocommit-ul
        hikariConfig.setConnectionTimeout(30000);
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * The constructor for the Database class
     */
    public Database() {}

    /**
     * It creates the connection if it doesn't exist
     * @return The connection
     * @throws SQLException An exception thrown if the connection fails
     */
    public synchronized static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    /**
     * It tries to create a new connection using the url, user and password
     * @throws SQLException An exception thrown if the connection fails
     */
    private static void createConnection() throws SQLException {
        if(hikariDataSource==null){
            throw new IllegalStateException("Not initialised");
        }
        if(hikariDataSource.getHikariPoolMXBean().getActiveConnections()==0){
            hikariDataSource.setAutoCommit(false);
            hikariDataSource.getConnection().close();
        }
    }

    /**
     * This method does the rollback until the last transaction that worked
     */
    public synchronized static void rollback() {
        try {
            if (hikariDataSource != null) {
                hikariDataSource.getConnection().rollback();
            }

        } catch (SQLException sqlException) {
            System.err.println(sqlException);
        }
    }

    /**
     * If the connection is active, this method closes it
     * @throws SQLException An exception thrown if the closing fails
     */
    public synchronized static void closeConnection() throws SQLException {
        if(hikariDataSource!=null){
            hikariDataSource.close();
        }

    }

    /**
     * It uses the commit operation in a JDBC transaction
     * @throws SQLException An exception thrown if the commit fails
     */
    public synchronized static void commit() throws SQLException{
        if(hikariDataSource!=null){
            hikariDataSource.getConnection().commit();
        }
    }
}