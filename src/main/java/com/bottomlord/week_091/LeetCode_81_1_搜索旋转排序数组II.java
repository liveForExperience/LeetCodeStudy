package com.bottomlord.week_091;

/**
 * @author ChenYue
 * @date 2021/4/7 8:29
 */
public class LeetCode_81_1_搜索旋转排序数组II {
    public boolean search(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[head] == nums[mid]) {
                head++;
            } else if (nums[head] < nums[mid]) {
                if (target == nums[head]) {
                    return true;
                }

                if (target > nums[head] && target < nums[mid]) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            } else {
                if (target == nums[tail]) {
                    return true;
                }

                if (target < nums[tail] && target > nums[mid]) {
                    head = mid + 1;
                } else {
                    tail = mid - 1;
                }
            }
        }

        return false;
    }
}
