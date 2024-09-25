package com.bottomlord.week_003;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/7/22 16:57
 */
public class LeetCode_136_2 {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length / 2 + 1);
        for (int num: nums) {
            if (map.containsKey(num)) {
                map.computeIfPresent(num, (k, v) -> v+=1);
            } else {
                map.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() < 2) {
                return entry.getKey();
            }
        }

        return -1;
    }
}