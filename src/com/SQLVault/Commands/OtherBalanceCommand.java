package com.SQLVault.Commands;

import com.SQLVault.Economy.Economy;
import com.SQLVault.Economy.EconomyUse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

/**
 * Created by Raymart on 7/14/2017.
 */
public class OtherBalanceCommand extends AbstractCommand {

    public OtherBalanceCommand(CommandSender sender) {
        super(sender, "mysql.econ.balance.other");
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        if(!isAuthorize()) {
            sendNoPermissionMessage();
            return;
        }
        Player player = getPlayer(args[0]);
        Economy economy = new EconomyUse(player);
        try {
            if(!economy.hasData()) {
                sendMessage("&7Player &a" + args[0] + " &7not found in database");
                return;
            }
            sendMessage("&a" + args[0] + "'s &8[ &aBalance&8 ] &7 || &ais : " + economy.getBalanceString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
