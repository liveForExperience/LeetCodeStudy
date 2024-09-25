package com.bottomlord.week_133;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-30 10:45:02
 */
public class LeetCode_2148_1_元素计数 {
    public int countElements(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int index = 0, sum = 0;
        for (Integer key : map.keySet()) {
            if (index == 0 || index == map.keySet().size() - 1) {
                index++;
                continue;
            }

            sum += map.get(key);

            index++;
        }

        return sum;
    }
}
