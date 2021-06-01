package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/1 8:24
 */
public class LeetCode_1150_3 {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length;
        int head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] >= target) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        if (nums[tail] != target) {
            return false;
        }

        int r = tail;
        head = 0;
        tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2 + 1;
            if (nums[mid] <= target) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return tail - r + 1 > len / 2;
    }
}
