package com.bottomlord.week_135;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-02-09 21:00:48
 */
public class LeetCode_offerII6_1_排序数组中两个数字之和 {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (map.containsKey(target - num)) {
                return new int[]{Math.min(i, map.get(num)), Math.max(i, map.get(num))};
            }
            map.put(num, i);
        }
        return null;
    }
}
