package com.SQLVault.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Raymart on 7/14/2017.
 */
public class EconomyExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("economy")) {
            if(args.length == 0) {
                AbstractCommand economy = new EconomyCommand(sender);
                economy.execute(sender, cmd, label, args);
                return true;
            }
            return true;
        }
        return false;
    }
}
