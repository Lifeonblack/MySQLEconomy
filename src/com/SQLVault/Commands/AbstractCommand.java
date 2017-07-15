package com.SQLVault.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Abstract class to design command
 */
public abstract class AbstractCommand {
    /**
     * Abstract Command field
     */
    private CommandSender sender;
    private String permission;

    /**
     * @param sender the command sender
     * @param permission the permission node needed
     */
    public AbstractCommand(CommandSender sender, String permission) {
        this.sender = sender;
        this.permission = permission;
    }

    /**
     * @return command sender
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * @return command sender as player
     */
    public Player getPlayer() {
        return (Player) sender;
    }

    /**
     * @return command sender as offline player
     */
    public OfflinePlayer getOffPlayer() {
        return getPlayer();
    }

    /**
     * @return true - if the sender is console
     */
    public boolean isSenderConsole() {
        return sender instanceof ConsoleCommandSender;
    }

    /**
     * @return true - if the sender is remote console
     */
    public boolean isSenderRemoteConsole() {
        return sender instanceof RemoteConsoleCommandSender;
    }

    /**
     * @return true - if the sender is player
     */
    public boolean isSenderPlayer() {
        return sender instanceof Player;
    }

    /**
     * This will check if the player has the permission given in the constructor
     * @return true - if the player is permitted
     */
    public boolean isAuthorize() {
        return sender.hasPermission(permission);
    }

    /**
     * Same as isAuthorize but has sub permission
     * @return true - if the player is permitted
     */
    public boolean isAuthorize(String subPerm) {
        return sender.hasPermission(permission + "." + subPerm);
    }

    /**
     * send the no permission message to player ( title )
     * @deprecated
     */
    public void sendNoPermissionMessage() {
        getPlayer().sendTitle(ChatColor.translateAlternateColorCodes('&', "&b&l&bMySQL Economy"), ChatColor.RED + "You have no permission to do that");
    }

    /**
     * send message method
     */
    public void sendMessage(String message) {
        getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * get OfflinePlayer by args
     * @param name playername
     */
    public OfflinePlayer getOfflinePlayer(String name) {
        return Bukkit.getOfflinePlayer(name);
    }

    /**
     * get player by args
     * @param name playername
     */
    public Player getPlayer(String name) {
        return Bukkit.getPlayer(name);
    }

    /**
     * Abstract void of command execution
     * @param sender command sender
     * @param cmd the command
     * @param label the command label
     * @param args the arguments
     */
    public abstract void execute(CommandSender sender, Command cmd, String label, String[] args);
}
