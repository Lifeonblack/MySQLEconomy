package com.SQLVault;

import com.SQLVault.Economy.EconomyUse;
import com.SQLVault.Listeners.PlayerJoin;
import com.SQLVault.Listeners.PlayerLeave;
import com.SQLVault.MySQL.MySQL;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Main class for this plugin
 */
public class MySQLEcon extends JavaPlugin {
    private String hostname = getConfig().getString("mysql.host");
    private  String database = getConfig().getString("mysql.database");
    private String username = getConfig().getString("mysql.user");
    private String password = getConfig().getString("mysql.pass");
    private int port = getConfig().getInt("mysql.port");
    private Statement statement;
    private MySQL mysql = new MySQL(hostname, port, database, username, password);
    private Connection c = null;
    private static MySQLEcon instance;

    /**
     * The main class constructor
     */
    public MySQLEcon() {}

    /**
     * Economy field for vault
     */
    public static Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;
        if(!setupEconomy()) {
            getLogger().severe("Disabled due to no dependancy found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        try {
            c = mysql.openConnection();
            statement = c.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS playerbalance (name varchar(16), balance DOUBLE PRECISION);");
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        for(Player player : Bukkit.getOnlinePlayers()) {
            com.SQLVault.Economy.Economy economy = new EconomyUse(player);
            try {
                economy.setBalance(economy.getVaultBalance());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        registerEvents();
    }


    /**
     * This will setup the economy,
     * and will check if the vault plugin is existed
     * @return true if the economy setup correctly
     */
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLeave(), this);
    }

    public Statement statement() {
        return statement;
    }

    public static MySQLEcon getInstance() {
        return instance;
    }

    public String getVersion() {
        return getDescription().getVersion();
    }
}
