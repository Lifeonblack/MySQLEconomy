package com.SQLVault.Listeners;

import com.SQLVault.Economy.Economy;
import com.SQLVault.Economy.EconomyUse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

/**
 * Player join event
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Economy economy = new EconomyUse(e.getPlayer());
        try {
            if(economy.hasData()) {
                return;
            }
            economy.setBalance(economy.getVaultBalance());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
