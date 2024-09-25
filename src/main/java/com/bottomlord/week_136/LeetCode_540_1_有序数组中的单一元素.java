package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-14 08:52:54
 */
public class LeetCode_540_1_有序数组中的单一元素 {
    public int singleNonDuplicate(int[] nums) {
        int num = 0;
        for (int i : nums) {
            num ^= i;
        }
        return num;
    }
}
