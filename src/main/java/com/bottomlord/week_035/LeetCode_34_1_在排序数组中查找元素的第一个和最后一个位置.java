package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/4 8:59
 */
public class LeetCode_34_1_在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        int head = 0, tail = nums.length - 1, index = -1;
        int[] ans = {-1, -1};

        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        if (index == -1) {
            return ans;
        }

        ans[0] = index;
        ans[1] = index;
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] == target) {
                ans[0] = i;
            } else {
                break;
            }
        }

        for (int i = index + 1; i < nums.length; i++) {
            if (nums[i] == target) {
                ans[1] = i;
            } else {
                break;
            }
        }

        return ans;
    }
}
