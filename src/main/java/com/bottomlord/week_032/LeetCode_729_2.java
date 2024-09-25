package com.bottomlord.week_032;

import java.util.TreeMap;

/**
 * @author ThinkPad
 * @date 2020/2/12 8:12
 */
public class LeetCode_729_2 {
    class MyCalendar {
        private TreeMap<Integer, Integer> calendar;
        public MyCalendar() {
            this.calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer pre = calendar.floorKey(start);
            Integer next = calendar.ceilingKey(start);

            if ((pre == null || start >= calendar.get(pre)) && (next == null || end <= next)) {
                calendar.put(start, end);
                return true;
            }

            return false;
        }
    }
}
