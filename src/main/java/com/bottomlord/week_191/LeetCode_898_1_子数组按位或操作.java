package com.bottomlord.week_191;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-03-09 08:46:05
 */
public class LeetCode_898_1_子数组按位或操作 {
    public int subarrayBitwiseORs(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            max = Math.max(num, max);
        }

        int mask = 1;
        while (max > mask) {
            mask <<= 1;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            set.add(cur);

            for (int j = i + 1; j < arr.length && cur != mask; j++) {
                cur |= arr[j];
                set.add(cur);
            }
        }

        return set.size();
    }
}
