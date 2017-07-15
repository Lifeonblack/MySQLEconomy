package com.SQLVault;

import com.SQLVault.Commands.BalanceExecutor;
import com.SQLVault.Commands.EconomyExecutor;
import com.SQLVault.Economy.EconomyUse;
import com.SQLVault.Listeners.PlayerJoin;
import com.SQLVault.Listeners.PlayerLeave;
import com.SQLVault.MySQL.MySQL;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
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
    /**
     * Economy field for vault
     */
    public static Economy econ = null;
    private String hostname = getConfig().getString("mysql.host");
    private  String database = getConfig().getString("mysql.database");
    private String username = getConfig().getString("mysql.user");
    private String password = getConfig().getString("mysql.pass");
    private int port = getConfig().getInt("mysql.port");
    private Statement statement;
    private MySQL mysql = new MySQL(hostname, port, database, username, password);
    private Connection c = null;
    private static MySQLEcon instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        setupEconomy();
        if(getServer().getPluginManager().getPlugin("Vault") == null) {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            getServer().getPluginManager().disablePlugin(this);
            String format = String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName());
            console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + format));
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
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }

        return (econ != null);
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLeave(), this);
    }

    private void registerCommands() {
        getCommand("balance").setExecutor(new BalanceExecutor());
        getCommand("economy").setExecutor(new EconomyExecutor());
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
