package com.platymuus.bukkit.eggtimer;

import java.io.*;
import java.util.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

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
        loadConfig(null);

        // Start the timer - every 20 ticks = every second
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() { timer(); }
        }, 0, 20);

        // Say hi
        PluginDescriptionFile pdfFile = getDescription();
        System.out.println(pdfFile.getName() + " v" + pdfFile.getVersion() + " enabled: " + timerEntries.size() + " entries");
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
                World world = getServer().getWorld(entry.world);
                Block block = world.getBlockAt(entry.x, entry.y, entry.z);
                if (world.isChunkLoaded(world.getChunkAt(block))) {
                    // Don't add items for unloaded chunks.
                    if (block.getType() == Material.CHEST) {
                        Chest chest = (Chest) block.getState();
                        Inventory inv = chest.getInventory();
                        // Clear it for now.
                        inv.clear();
                        ItemStack stack = new ItemStack(entry.item, entry.count, (short) entry.data, (byte) entry.data);
                        inv.addItem(stack);
                    }
                }
            }
        }
    }

    public void loadConfig(Player player) {
        timerEntries.clear();
        this.getDataFolder().mkdirs();
        File f = new File(getDataFolder(), "locations.txt");
        try {
            int line = 0;
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                ++line;

                String[] split = in.nextLine().split("=");
                if (split.length < 2) continue;
                if (split[0].charAt(0) == '#') continue;

                String[] coords = split[0].split(",");
                String[] item = split[1].split(",");
                if (coords.length != 4) {
                    send(player, "[EggTimer] Expected 4x coords on line " + line);
                    continue;
                } else if (item.length != 4) {
                    send(player, "[EggTimer] Expected 4x item info on line " + line);
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
                    send(player, "[EggTimer] Not a number value on line " + line);
                    continue;
                }

                // TODO: Sanity checks.

                World world = getServer().getWorld(entry.world);
                if (world == null) {
                    send(player, "[EggTimer] " + entry.world + " unloaded, not checking validity on line " + line);
                } else {
                    Block block = world.getBlockAt(entry.x, entry.y, entry.z);
                    if (block.getType() != Material.CHEST) {
                        send(player, "[EggTimer] Block is not a chest on line " + line);
                        boolean found = false;
                        for (int dx = -1; dx <= 1; ++dx) {
                            for (int dy = -1; dy <= 1; ++dy) {
                                for (int dz = -1; dz <= 1; ++dz) {
                                    if (block.getRelative(dx, dy, dz).getType() == Material.CHEST) {
                                        send(player, "(did you mean " + (entry.x+dx) + "," + (entry.y+dy) + "," + (entry.z+dz) + "?)");
                                        found = true; break;
                                    }
                                }
                                if (found) break;
                            }
                            if (found) break;
                        }
                        continue;
                    }
                }

                timerEntries.add(entry);
            }
        }
        catch (FileNotFoundException ex) {
            send(player, "[EggTimer] Could not find locations.txt.");
        }
    }

    private void send(Player player, String text) {
        if (player == null) {
            System.out.println(text);
        } else {
            player.sendMessage(ChatColor.GRAY + text);
        }
    }

}
