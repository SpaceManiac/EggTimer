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

    private final ArrayList<TimerEntry> timerEntries = new ArrayList<TimerEntry>();
    private int currentTime = 0;

    @Override
    public void onEnable() {
        // Register our commands
        getCommand("eggtimer").setExecutor(new EggTimerCommand(this));

        // Load config
        loadConfig();

        // Start the timer - every 20 ticks = every second
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() { timer(); }
        }, 0, 20);

        // Say hi
        PluginDescriptionFile pdfFile = getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled");
    }

    @Override
    public void onDisable() {
        // I think it does this anyways, but just to make sure.
        getServer().getScheduler().cancelTasks(this);
    }

    private void timer() {
        ++currentTime;

        for (TimerEntry entry : timerEntries) {
            if (currentTime % entry.interval == 0) {
                
            }
        }
    }

    public void loadConfig() {
        timerEntries.clear();
        this.getDataFolder().mkdirs();
        File f = new File(getDataFolder(), "locations.txt");
        try {
            int line = 1;
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String[] split = in.nextLine().split("=");
                if (split.length < 2) continue;
                if (split[0].charAt(0) == '#') continue;

                String[] coords = split[0].split(",");
                String[] item = split[0].split(",");
                if (coords.length != 4) {
                    System.out.println("[EggTimer] Expected 4x coords on line " + line);
                    continue;
                } else if (item.length != 4) {
                    System.out.println("[EggTimer] Expected 4x item info on line " + line);
                    continue;
                }

                TimerEntry entry = new TimerEntry();
                entry.world = coords[0];
                try {
                    entry.x = Integer.parseInt(coords[1]);
                    entry.y = Integer.parseInt(coords[2]);
                    entry.z = Integer.parseInt(coords[3]);
                    entry.item = Integer.parseInt(item[0]);
                    entry.count = Integer.parseInt(item[1]);
                    entry.data = Integer.parseInt(item[2]);
                    entry.interval = Integer.parseInt(item[3]);
                }
                catch (NumberFormatException ex) {
                    System.out.println("[EggTimer] Not a number value on line " + line);
                    continue;
                }

                // TODO: Sanity checks.

                timerEntries.add(entry);
                
                ++line;
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("[EggTimer] Could not find locations.txt.");
        }
    }

}
