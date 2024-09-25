package com.bottomlord.week_043;

/**
 * @author ChenYue
 * @date 2020/4/27 8:05
 */
public class LeetCode_33_1_搜索旋转排序数组 {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[head] < nums[mid]) {
                if (target < nums[mid] && target >= nums[head]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[tail]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return -1;
    }
}
