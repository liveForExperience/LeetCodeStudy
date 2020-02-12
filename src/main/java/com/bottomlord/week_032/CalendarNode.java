package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/12 8:21
 */
public class CalendarNode {
    public int start;
    public int end;
    public CalendarNode left;
    public CalendarNode right;

    public CalendarNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
