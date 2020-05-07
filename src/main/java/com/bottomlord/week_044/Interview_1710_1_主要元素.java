package com.bottomlord.week_044;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/5/7 8:23
 */
public class Interview_1710_1_主要元素 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                return entry.getKey();
            }
        }

        return -1;
    }
}
