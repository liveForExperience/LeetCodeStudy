package com.bottomlord.week_142;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-04-01 10:54:21
 */
public class LeetCode_954_1_二倍数对数组 {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(arr);

        int n = arr.length, half = n / 2, count = 0;

        for (int num : arr) {
            if (map.getOrDefault(num, 0) <= 0) {
                continue;
            }

            map.put(num, map.get(num) - 1);

            if (map.getOrDefault(num * 2, 0) <= 0) {
                map.put(num, map.get(num) + 1);
                continue;
            }

            map.put(num * 2, map.get(num * 2) - 1);
            count++;
        }

        return count == half;
    }
}
