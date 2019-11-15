package com.bottomlord.week_019;

public class LeetCode_80_1_删除排序数组中的重复项 {
    public int removeDuplicates(int[] nums) {
        int index = 1, count = 0, len = nums.length;
        while (index < len) {
            if (nums[index] == nums[index - 1]) {
                count++;
                if (count >= 2) {
                    int tmpIndex = index;
                    while (tmpIndex < len - 1) {
                        int tmp = nums[tmpIndex];
                        nums[tmpIndex] = nums[tmpIndex + 1];
                        nums[tmpIndex + 1] = tmp;
                        tmpIndex++;
                    }
                    len--;
                    count--;
                } else {
                    index++;
                }
            } else {
                count = 0;
                index++;
            }
        }

        return len;
    }
}
