package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/4 8:46
 */
public class Interview_53I_1_在排序数组中查找数字 {
    public int search(int[] nums, int target) {
        int head = 0, tail = nums.length - 1, index = -1, count = 0;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;

            if (nums[mid] == target) {
                index = mid;
                count++;
                break;
            } else if (nums[mid] < target) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        if (index == -1) {
            return 0;
        }

        for (int i = index; i >= 0; i--) {
            if (nums[i] == target) {
                count++;
            } else {
                break;
            }
        }

        for (int i = index; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}
