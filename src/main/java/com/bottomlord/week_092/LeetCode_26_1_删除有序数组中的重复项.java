package com.bottomlord.week_092;

public class LeetCode_26_1_删除有序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (i + 1 < nums.length && cur == nums[i + 1]) {
                i++;
            }
            nums[index++] = cur;
        }

        return index;
    }
}
