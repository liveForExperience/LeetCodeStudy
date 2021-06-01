package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/5/31 9:03
 */
public class LeetCode_1150_2 {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            if (nums[head] < target) {
                head++;
            } else if (nums[head] > target) {
                return false;
            }

            if (nums[tail] > target) {
                tail--;
            } else if (nums[tail] < target) {
                return false;
            }

            if (nums[head] == nums[tail] && nums[head] == target) {
                break;
            }
        }

        return tail - head + 1 > len / 2;
    }
}
