package com.bottomlord.week_045;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/5/15 8:52
 */
public class LeetCode_560_2 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, ans = 0;
        for (int num : nums) {
            sum += num;
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}