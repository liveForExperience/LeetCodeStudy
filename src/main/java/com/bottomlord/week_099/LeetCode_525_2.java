package com.bottomlord.week_099;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/6/3 8:22
 */
public class LeetCode_525_2 {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int len = i + 1, diff = len - 2 * sum;
            if (map.containsKey(diff)) {
                ans = Math.max(ans, len - map.get(diff));
            } else {
                map.put(diff, i + 1);
            }
        }

        return ans;
    }
}
