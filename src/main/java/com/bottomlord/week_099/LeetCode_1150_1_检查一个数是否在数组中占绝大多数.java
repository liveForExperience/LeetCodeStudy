package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/5/31 8:53
 */
public class LeetCode_1150_1_检查一个数是否在数组中占绝大多数 {
    public boolean isMajorityElement(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            } else if (num > target) {
                break;
            }
        }

        return count > nums.length / 2;
    }
}
