package com.bottomlord.week_192;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-16 19:29:48
 */
public class LeetCode_2488_1_统计中位数为K的子数组 {
    public int countSubarrays(int[] nums, int k) {
        int index = 0, n = nums.length;
        for (; index < n; index++) {
            if (k == nums[index]) {
                break;
            }
        }

        int sum = 0, ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = index + 1; i < n; i++) {
            sum += nums[i] > k ? 1 : -1;
            if (sum == 0 || sum == 1) {
                ans++;
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        sum = 0;
        for (int i = index - 1; i >= 0; i--) {
            sum += nums[i] > k ? 1 : -1;
            if (sum == 0 || sum == 1) {
                ans++;
            }

            ans += map.getOrDefault(-sum, 0) + map.getOrDefault(1 - sum, 0);
        }

        return ans;
    }
}
