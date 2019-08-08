package com.bottomlord.week_5;

public class LeetCode_704_1_二分查找 {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head < tail) {
            int mid = (tail - head) / 2 + head;

            int num = nums[mid];

            if (num == target) {
                return mid;
            }

            if (num > target) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }
        return -1;
    }
}
