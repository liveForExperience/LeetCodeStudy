package com.bottomlord.week_034;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/28 12:11
 */
public class Interview_39_1_数组中出现次数超过一半的数字 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int target = nums.length / 2 + 1;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == target) {
                return num;
            }
        }

        return -1;
    }
}
