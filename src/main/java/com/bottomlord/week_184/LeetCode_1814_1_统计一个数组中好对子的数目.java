package com.bottomlord.week_184;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-01-17 08:39:40
 */
public class LeetCode_1814_1_统计一个数组中好对子的数目 {
    public int countNicePairs(int[] nums) {
        int ans = 0, mod = 1000000007;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            int rev = 0, cur = num;
            while (num > 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }

            int key = cur - rev;
            ans = (ans + map.getOrDefault(key, 0)) % mod;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }
}
