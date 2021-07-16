package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/16 8:23
 */
public class LeetCode_offer53_1_在排序数组中查找数字I {
    public int search(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num > target) {
                break;
            }

            if (num == target) {
                count++;
            }
        }

        return count;
    }
}
