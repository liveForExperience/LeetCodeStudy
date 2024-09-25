package com.bottomlord.week_092;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/4/16 12:57
 */
public class LeetCode_523_2 {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        map.put(0, -1);
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
