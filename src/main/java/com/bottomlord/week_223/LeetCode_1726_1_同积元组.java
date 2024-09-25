package com.bottomlord.week_223;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-10-19 10:28:07
 */
public class LeetCode_1726_1_同积元组 {
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length, sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int multi = nums[i] * nums[j];
                map.merge(multi, 1, Integer::sum);
            }
        }

        for (Integer num : map.values()) {
            sum += (num) * (num - 1) / 2;
        }

        return sum << 3;
    }
}
