package com.SQLVault.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Raymart on 7/14/2017.
 */
public class MyCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("balance")) {
            if(args.length == 0) {
                AbstractCommand balance = new BalanceCommand(sender);
                balance.execute(sender, cmd, label, args);
                return true;
            }
            if(args.length > 1) {
                AbstractCommand balance = new OtherBalanceCommand(sender);
                balance.execute(sender, cmd, label, args);
                return true;
            }
            return true;
        }
        return false;
    }
}
