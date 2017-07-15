package com.SQLVault.Commands;

import com.SQLVault.MySQLEcon;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by Raymart on 7/14/2017.
 */
public class EconomyCommand extends AbstractCommand {

    public EconomyCommand(CommandSender sender) {
        super(sender, "mysql.econ.admin");
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        if(!isAuthorize()) {
            sendNoPermissionMessage();
            return;
        }
        sendMessage("&b&lMySQL Economy v" + MySQLEcon.getInstance().getVersion() + " : >>");
        sendMessage("&8[ &aset &8] &7to set player's balance");
        sendMessage("&8[ &areset &8] &7to reset player's balance");
        sendMessage("&8[ &atake &8] &7take player's money");
        sendMessage("&8[ &aadd &8] &7add player's money");
        return;
    }
}
