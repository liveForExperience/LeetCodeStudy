package com.bottomlord.week_070;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/11/9 8:44
 */
public class LeetCode_352_3 {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, sum = 0;
        int[] sums = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
            map.put(sum, i);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = sums[i] - k + nums[i];
            if (map.containsKey(num)) {
                max = Math.max(max, i - map.get(num) + 1);
            }
        }

        return max;
    }
}
