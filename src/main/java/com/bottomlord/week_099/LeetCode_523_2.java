package com.bottomlord.week_099;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/6/2 8:18
 */
public class LeetCode_523_2 {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + nums[i]) % k;

            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
