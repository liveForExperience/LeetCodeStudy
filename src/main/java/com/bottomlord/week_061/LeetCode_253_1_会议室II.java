package com.bottomlord.week_061;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/9/3 8:39
 */
public class LeetCode_253_1_会议室II {
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) {
            return 0;
        }

        Arrays.sort(intervals, (x1, x2) -> {
            if (x1[0] == x2[0]) {
                return Integer.compare(x1[1], x2[1]);
            }

            return Integer.compare(x1[0], x2[0]);
        });

        boolean[] memo = new boolean[len];
        int ans = 0;

        while (!allDone(memo)) {
            ans++;
            int[] pre = {-1, -1};
            for (int i = 0; i < len; i++) {
                if (memo[i] || intervals[i][0] < pre[1]) {
                    continue;
                }

                memo[i] = true;
                pre = intervals[i];
            }
        }

        return ans;
    }

    private boolean allDone(boolean[] memo) {
        for (boolean m : memo) {
            if (!m) {
                return false;
            }
        }

        return true;
    }
}
