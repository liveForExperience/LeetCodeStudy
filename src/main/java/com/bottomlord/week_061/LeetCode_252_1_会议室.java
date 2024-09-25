package com.bottomlord.week_061;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ChenYue
 * @date 2020/9/3 8:18
 */
public class LeetCode_252_1_会议室 {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                return false;
            }
        }

        return true;
    }
}
