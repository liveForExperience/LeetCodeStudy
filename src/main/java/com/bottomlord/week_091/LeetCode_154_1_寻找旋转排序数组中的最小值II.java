package com.bottomlord.week_091;

/**
 * @author ChenYue
 * @date 2021/4/9 8:27
 */
public class LeetCode_154_1_寻找旋转排序数组中的最小值II {
    public int findMin(int[] nums) {
        int len = nums.length, head = 0, tail = len - 1, min = Integer.MAX_VALUE;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == nums[head]) {
                min = Math.min(nums[head], min);
                head++;
            } else if (nums[mid] > nums[head]) {
                min = Math.min(nums[head], min);
                head = mid + 1;
            } else {
                min = Math.min(nums[mid], min);
                tail = mid;
            }
        }

        return Math.min(nums[head], min);
    }
}
