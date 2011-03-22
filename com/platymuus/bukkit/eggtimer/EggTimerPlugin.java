package com.platymuus.bukkit.eggtimer;

import java.io.*;
import java.util.*;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for Egg Timer plugin.
 * @author Tad
 */
public class EggTimerPlugin extends JavaPlugin {

    public void onEnable() {
        // Register our commands
        getCommand("eggtimer").setExecutor(new EggTimerCommand(this));

        // Say hi
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled");
    }

}
