package com.bottomlord.week_199;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-05-03 22:07:29
 */
public class LeetCode_962_1_最大宽度坡 {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, Comparator.comparingInt(x -> nums[x]));

        int min = n, ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, indexes[i] - min);
            min = Math.min(min, indexes[i]);
        }

        return ans;
    }
}
