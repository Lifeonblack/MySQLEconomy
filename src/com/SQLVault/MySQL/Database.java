package com.SQLVault.MySQL;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Abstract class for database
 */
public abstract class Database {

    /**
     * Field Connection
     */

    protected Connection connection;

    /**
     * Protected database
     */
    protected Database() {
        this.connection = null;
    }

    /**
     *
     * @return to open a certain type of connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public abstract Connection openConnection() throws SQLException, ClassNotFoundException;

    /**
     * @return true - if the connection is existed or open
     * @throws SQLException
     */
    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    /**
     * @return to connection field
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * This will close the connection
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        if(connection == null) {
            return;
        }
        connection.close();
        return;
    }

}
