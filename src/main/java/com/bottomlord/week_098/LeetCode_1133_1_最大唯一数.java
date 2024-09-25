package com.bottomlord.week_098;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ChenYue
 * @date 2021/5/29 22:12
 */
public class LeetCode_1133_1_最大唯一数 {
    public int largestUniqueNumber(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1;
    }
}
