package com.platymuus.bukkit.eggtimer;

import org.bukkit.ChatColor;
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
        return false;
    }
    
}
