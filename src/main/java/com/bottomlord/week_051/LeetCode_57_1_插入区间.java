package com.bottomlord.week_051;

import java.util.LinkedList;

/**
 * @author ChenYue
 * @date 2020/6/22 8:37
 */
public class LeetCode_57_1_插入区间 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        LinkedList<int[]> list = new LinkedList<>();
        int newStart = newInterval[0], newEnd = newInterval[1];
        int index = 0, len = intervals.length;

        while (index < len && newStart > intervals[index][0]) {
            list.add(intervals[index++]);
        }

        if (list.isEmpty() || list.getLast()[1] < newStart) {
            list.add(newInterval);
        } else {
            int[] interval = list.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            list.add(interval);
        }

        while (index < len) {
            if (list.getLast()[1] < intervals[index][0]) {
                list.add(intervals[index++]);
            } else {
                int[] interval = list.removeLast();
                interval[1] = Math.max(interval[1], intervals[index++][1]);
                list.add(interval);
            }
        }

        return list.toArray(new int[0][0]);
    }
}
