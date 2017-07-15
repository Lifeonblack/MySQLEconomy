package com.SQLVault.Economy;

import org.bukkit.OfflinePlayer;

/**
 * Abstract class implementing Economy Interface
 */
public abstract class AbstractEconomy implements Economy {
    private OfflinePlayer player;

    /**
     * Economy Utility constructor
     * @param player is the offlineplayer we have to use
     */
    public AbstractEconomy(OfflinePlayer player) {
        this.player = player;
    }

    /**
     * @return to the player input in construction
     */
    public OfflinePlayer getPlayer() {
        return this.player;
    }

    public String getPlayerName() {
        return this.player.getName();
    }
}
