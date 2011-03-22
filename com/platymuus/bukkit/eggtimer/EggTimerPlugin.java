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

    @Override
    public void onEnable() {
        // Register our commands
        getCommand("eggtimer").setExecutor(new EggTimerCommand(this));

        // Start the timer - every 20 ticks = every second
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() { timer(); }
        }, 0, 20);

        // Say hi
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        // I think it does this anyways, but just to make sure.
        getServer().getScheduler().cancelTasks(this);
    }

    public void timer() {
        // TODO
    }

    public void loadConfig() {
        this.getDataFolder().mkdirs();
        File f = new File(this.getDataFolder().getPath() + File.pathSeparator + "locations.txt");
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String line = in.nextLine();
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("[EggTimer] Warning: could not find locations.txt.");
        }
    }

}
