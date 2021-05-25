package com.bottomlord.week_098;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/25 8:06
 */
public class LeetCode_1787_1_使所有区间的异或结果为零 {
    private final int max = 1 << 10;
    private final int infinity = Integer.MAX_VALUE / 2;

    public int minChanges(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[max];
        Arrays.fill(dp, infinity);
        dp[0] = 0;

        for (int i = 0; i < k; i++) {
            Map<Integer, Integer> countMapping = new HashMap<>();
            int size = 0;
            for (int j = i; j < len; j += k) {
                countMapping.put(nums[j], countMapping.getOrDefault(nums[j], 0) + 1);
                size++;
            }

            int lastGroupMin = Arrays.stream(dp).min().getAsInt();
            int[] tmpDp = new int[max];
            Arrays.fill(tmpDp, lastGroupMin);

            for (int xor = 0; xor < max; xor++) {
                for (Integer x : countMapping.keySet()) {
                    tmpDp[xor] = Math.min(tmpDp[xor], dp[xor ^ x] - countMapping.get(x));
                }
            }

            for (int index = 0; index < tmpDp.length; index++) {
                tmpDp[index] += size;
            }

            dp = tmpDp;
        }

        return dp[0];
    }
}
