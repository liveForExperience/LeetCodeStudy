package com.bottomlord.week_191;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-10 20:52:15
 */
public class LeetCode_1590_1_使数组和能被P整除 {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
            map.put(sums[i] % p, i);
        }

        if (sums[n] % p == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int val = cal(sums[n], nums[i], p);
            if (map.containsKey(val)) {
                if (map.get(val) < i) {
                    ans = Math.min(i - map.get(val), ans);
                }
            }
        }

        return ans;
    }

    private int cal(int sum, int num, int p) {
        return ((num % p) - (sum % p) + p) % p;
    }
}
