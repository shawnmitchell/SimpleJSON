package com.company;

/**
 * Created by shawn on 8/15/14.
 */
public class MarathonRunner implements Comparable<MarathonRunner> {
    private int time = 0;
    private String name = null;

    public MarathonRunner(int time, String name) {
        this.time = time;
        this.name = name;
    }

    public int getTime() {
        if (time == 0)
            throw new IllegalStateException("time not set");
        return time;
    }

    public String getName() {
        if (name == null)
            throw new IllegalStateException("name not set");
        return name;
    }

    @Override
    public int compareTo(MarathonRunner that) {
        return this.getTime() - that.getTime();
    }

    @Override
    public String toString() {
        return getName() + " ran the marathon in " + getTime() + " minutes.";

    }
}
