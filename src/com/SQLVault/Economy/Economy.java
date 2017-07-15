package com.SQLVault.Economy;

import java.sql.SQLException;

/**
 * Economy interface
 */
public interface Economy {


    /**
     * This will add balance to player
     * @param balance the balance to add
     * @throws SQLException
     */
    public void addBalance(double balance) throws SQLException;

    /**
     * This will check the player's balance on database
     * @return player balance ( Database )
     * @throws SQLException
     */
    public double getBalance() throws SQLException;

    /**
     * This will remove balance to player without checking,
     * if the transaction succeed or not
     * @param balance the balance to get
     * @throws SQLException
     */
    public void minusBalance(double balance) throws SQLException;

    /**
     * @return to player balance ( Vault )
     */
    public double getVaultBalance();

    /**
     * This will check if the player has data on database
     * @return true - if player data exist
     * @throws SQLException
     */
    public boolean hasData() throws SQLException;

    /**
     * This will set player's balance
     * @param balance amount of the balance to set
     */
    public void setBalance(double balance) throws SQLException;

    /**
     * This will set player's balance using string
     * @param balance string balance to set
     * @throws SQLException
     * @deprecated
     * use the setBalance(double)
     */
    @Deprecated
    public void setBalance(String balance) throws SQLException;

    /**
     * This will prepare the transaction
     * @param balance the balance to take
     * @throws SQLException
     */
    public void takeBalance(double balance) throws SQLException;

    /**
     * Check the transaction status
     * @return true - if the transaction succeed
     */
    public boolean transactionSuccess();

    /**
     * get the balance using string
     * @return string balance
     */
    public String getBalanceString() throws SQLException;
}
