package com.bottomlord.week_189;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-02-26 20:13:37
 */
public class LeetCode_823_1_带因子的二叉树 {
    public int numFactoredBinaryTrees(int[] arr) {
        int mod = (int)1e9 + 7;
        long sum = 0;
        Map<Long, Long> map = new HashMap<>(arr.length);
        for (int num : arr) {
            map.put((long) num, 1L);
        }

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                long multi = (long)arr[i] * arr[j];
                if (!map.containsKey(multi)) {
                    continue;
                }

                long timeValue = map.get((long)arr[i]) * map.get((long)arr[j]);
                if (arr[i] == arr[j]) {
                    map.put(multi, map.get(multi) + timeValue);
                } else {
                    map.put(multi, map.get(multi) + 2 * timeValue);
                }
            }
            sum += map.get((long)arr[i]);
            sum %= mod;
        }

        return (int) sum;
    }
}
