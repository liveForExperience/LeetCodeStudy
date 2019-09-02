package com.bottomlord.week_007;

public class LeetCode_35_2 {
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        int head = 0, tail = nums.length - 1, mid = 0;
        while (head < tail) {
            mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > target) {
                tail = mid;
            }

            if (nums[mid] < target) {
                head = mid + 1;
            }
        }

        return tail;
    }
}