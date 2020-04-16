package com.bottomlord.week_041;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/4/16 7:47
 */
public class LeetCode_56_1_合并区间 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x1, x2) -> {
            if (x1[0] != x2[0]) {
                return x1[0] - x2[0];
            } else {
                return x1[1] - x2[1];
            }
        });

        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < l) {
                ans.add(interval);
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(r, ans.get(ans.size() - 1)[1]);
            }
        }

        return ans.toArray(new int[0][0]);
    }
}
