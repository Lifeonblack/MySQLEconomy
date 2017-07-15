package com.SQLVault.Commands;

import com.SQLVault.Economy.Economy;
import com.SQLVault.Economy.EconomyUse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

/**
 * Balance Command using Abstract Command
 */
public class BalanceCommand extends AbstractCommand {

    public BalanceCommand(CommandSender sender) {
        super(sender, "mysql.econ.balance");
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        if(!isAuthorize()) {
            sendNoPermissionMessage();
            return;
        }
        Economy economy = new EconomyUse(getOffPlayer());
        try {
            if(!economy.hasData()) {
                sendMessage("&7Player &a" + args[0] + " &7not found in database");
                return;
            }
            sendMessage("&aYour &8[ &aBalance&8 ] &7 || &ais : " + economy.getBalanceString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
