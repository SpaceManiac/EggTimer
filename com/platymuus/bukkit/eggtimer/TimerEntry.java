package com.platymuus.bukkit.eggtimer;

/**
 * Entry in the list of egg timers.
 * @author Tad
 */
public class TimerEntry {

    public String world;
    public int x, y, z;
    public int item, count, data, interval;

    public TimerEntry() {}

    public TimerEntry(String world, int x, int y, int z, int item, int count, int data, int interval) {
        this.world = world;
        this.x = x; this.y = y; this.z = z;
        this.item = item; this.count = count;
        this.data = data; this.interval = interval;
    }

}
