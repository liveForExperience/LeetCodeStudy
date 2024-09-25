package com.bottomlord.week_104;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/7/8 8:16
 */
public class LeetCode_930_2 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
