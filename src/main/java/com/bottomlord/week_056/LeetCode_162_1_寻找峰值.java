package com.bottomlord.week_056;

/**
 * @author ChenYue
 * @date 2020/7/31 8:53
 */
public class LeetCode_162_1_寻找峰值 {
    public int findPeakElement(int[] nums) {
        return binarySearch(nums, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int head, int tail) {
        if (head == tail) {
            return head;
        }

        int mid = head + (tail - head) / 2;

        return nums[mid] > nums[mid + 1] ? binarySearch(nums, head, mid) : binarySearch(nums, mid + 1, tail);
    }
}
