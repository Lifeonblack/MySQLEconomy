package com.SQLVault.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySQL database
 */
public class MySQL extends Database {

    private final String user;
    private final String database;
    private final String password;
    private final int port;
    private final String hostname;

    /**
     * @param hostname host of a database
     * @param port port of a database
     * @param username username of a database
     * @param password password of a database
     */
    public MySQL(String hostname, int port, String username,
                 String password) {
        this(hostname, port, null, username, password);
    }

    /**
     * @param hostname host of a database
     * @param port port of a database
     * @param username username of a database
     * @param password password of a database
     */
    public MySQL(String hostname, int port, String database,
                 String username, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = username;
        this.password = password;
    }

    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        String connectionURL = "jdbc:mysql://"
                + this.hostname + ":" + this.port;
        if (database != null) {
            connectionURL = connectionURL + "/" + this.database + "?useSSL=false";
        }

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionURL,
                this.user, this.password);
        return connection;
    }

}
