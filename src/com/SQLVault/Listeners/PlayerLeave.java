package com.SQLVault.Listeners;

import com.SQLVault.Economy.Economy;
import com.SQLVault.Economy.EconomyUse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

/**
 * Player leave event
 */
public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Economy economy = new EconomyUse(e.getPlayer());
        try {
            if(economy.getBalance() == economy.getVaultBalance()) {
                return;
            }
            economy.setBalance(economy.getVaultBalance());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
