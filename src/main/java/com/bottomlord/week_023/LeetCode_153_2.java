package com.bottomlord.week_023;

public class LeetCode_153_2 {
    public int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int head = 0, tail = nums.length - 1;

        if (nums[tail] > nums[head]) {
            return nums[head];
        }

        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[mid] < nums[head]) {
                tail = mid - 1;
            } else {
                head = mid + 1;
            }
        }

        return -1;
    }
}
