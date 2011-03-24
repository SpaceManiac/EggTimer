package com.platymuus.bukkit.eggtimer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Handler for the /eggtimer command to admin EggTimer
 * @author Tad
 */
public class EggTimerCommand implements CommandExecutor {

    private final EggTimerPlugin plugin;

    public EggTimerCommand(EggTimerPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (split.length == 0) return false;
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.GRAY + "You don't have permission to use this command");
            return true;
        }

        plugin.loadConfig((Player) sender);
        return true;
    }
    
}
