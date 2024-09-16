package com.bottomlord.week_271;

import java.util.List;

/**
 * @author chen yue
 * @date 2024-09-16 19:57:15
 */
public class LeetCode_2848_1_与车相交的点 {
    public int numberOfPoints(List<List<Integer>> nums) {
        int maxEnd = 0;
        for (List<Integer> interval : nums) {
            maxEnd = Math.max(maxEnd, interval.get(1));
        }

        int[] diff = new int[maxEnd + 2];
        for (List<Integer> interval : nums) {
            diff[interval.get(0)]++;
            diff[interval.get(1) + 1]--;
        }

        int cur = 0, ans = 0;
        for (int d : diff) {
            cur += d;
            ans = ans + (cur > 0 ? 1 : 0);
        }

        return ans;
    }
}
