package com.bottomlord.week_032;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/2/13 17:15
 */
public class Interview_03_1_数组中的重复数字 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }

            set.add(num);
        }

        return -1;
    }
}
