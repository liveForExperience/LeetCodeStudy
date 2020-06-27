package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/27 21:05
 */
public class LeetCode_81_1_搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[head] == nums[mid]) {
                head++;
            } else if (nums[head] < nums[mid]) {
                if (nums[head] == target) {
                    return true;
                } else if (nums[head] < target && target < nums[mid]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (nums[tail] == target) {
                    return true;
                } else if (nums[mid] < target && target < nums[tail]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return false;
    }
}
