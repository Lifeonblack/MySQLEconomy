package com.SQLVault.Economy;

import com.SQLVault.MySQLEcon;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import static com.SQLVault.MySQLEcon.econ;

/**
 * The class usage of Economy
 */
public class EconomyUse extends AbstractEconomy {
    /**
     * Offline player used as field
     */
    private boolean transaction = false;

    public EconomyUse(OfflinePlayer player) {
        super(player);
        transaction = false;
    }

    public double getVaultBalance() {
        return econ.getBalance(getPlayer());
    }

    public double getBalance() throws SQLException {
        ResultSet res = MySQLEcon.getInstance().statement().executeQuery("SELECT * FROM playerbalance WHERE name = '" + getPlayerName() + "';");
        if(res.next()) {
            return res.getDouble("balance");
        }
        MySQLEcon.getInstance().statement().executeUpdate("INSERT INTO playerbalance (name, balance) VALUES ('" + getPlayerName() + "', 0.0);");
        return 0.0;
    }

    public void setBalance(double balance) throws SQLException {
        if(!hasData()) {
            MySQLEcon.getInstance().statement().executeUpdate("INSERT INTO playerbalance (name, balance) VALUES ('" + getPlayerName() + "', " + balance + ");");
            return;
        }
        MySQLEcon.getInstance().statement().executeUpdate("UPDATE playerbalance SET balance = " + balance + " WHERE name = '" + getPlayerName() + "';");
        return;
    }

    @Deprecated
    public void setBalance(String balance) throws SQLException {
        try {
            Double.parseDouble(balance);
        }catch(NumberFormatException e) {
            e.printStackTrace();
        }
        setBalance(Double.parseDouble(balance));
    }

    public void takeBalance(double balance) throws SQLException {
        if(getBalance() > balance) {
            transaction = false;
        }
        minusBalance(balance);
        transaction = true;
    }

    public void addBalance(double balance) throws SQLException {
        setBalance(getBalance() + balance);
    }

    public void minusBalance(double balance) throws SQLException {
        setBalance(getBalance() - balance);
    }

    public boolean hasData() throws SQLException {
        ResultSet res = MySQLEcon.getInstance().statement().executeQuery("SELECT * FROM playerbalance WHERE name = '" + getPlayerName() + "';");
        return res.next();
    }

    public boolean transactionSuccess() {
        return transaction;
    }

    public String getBalanceString() throws SQLException {
        return commas(getBalance());
    }

    public String commas(double amount) {
        if(amount > 999.99D) {
            DecimalFormat formatter = new DecimalFormat("#,###");
            String number = formatter.format(amount);
            return number;
        }
        if(amount > 999999.99D) {
            DecimalFormat formatter = new DecimalFormat("#,###,###");
            String number = formatter.format(amount);
            return number;
        }
        if(amount == 0) {
            return "0";
        }
        DecimalFormat formatter = new DecimalFormat("###.00");
        String number = formatter.format(amount);
        return number;
    }
}
