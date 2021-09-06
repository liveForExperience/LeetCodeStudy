package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-06 10:27:04
 */
public class LeetCode_704_1_二分查找 {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return -1;
    }
}
