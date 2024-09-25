package com.bottomlord.week_084;

import java.util.TreeMap;

/**
 * @author ChenYue
 * @date 2021/2/21 15:10
 */
public class LeetCode_1438_2 {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int len = nums.length, ans = 0, l = 0;
        for (int r = 0; r < len; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
