package com.SQLVault.MySQL;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SQLite database
 */
public class SQLite extends Database {

    private final String dbLocation;

    /**
     * @param dbLocation the database location, to open connection
     */
    public SQLite(String dbLocation) {
        this.dbLocation = dbLocation;
    }

    @Override
    public Connection openConnection() throws SQLException,
            ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }

        File dataFolder = new File("sqlite-db/");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        File file = new File(dataFolder, dbLocation);
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create database!");
            }
        }
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager
                .getConnection("jdbc:sqlite:"
                        + dataFolder + "/"
                        + dbLocation + "?useSSL=false");
        return connection;
    }

}
