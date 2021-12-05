package com.bottomlord.week_125;

public class LeetCode_1909_1_删除一个元素使数组严格递增 {
    public boolean canBeIncreasing(int[] nums) {
        int x = -1, y = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                x = i;
                y = i - 1;
                break;
            }
        }

        if (x == -1) {
            return true;
        }

        boolean flag = true;
        for (int i = 1; i < nums.length; i++) {
            if (i == x) {
                continue;
            }

            int c = i - 1;
            if (i - 1 == x) {
                c = i - 2;
            }

            if (nums[i] <= nums[c]) {
                flag = false;
                break;
            }
        }

        if (flag) {
            return true;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (i == y) {
                continue;
            }

            int c = i + 1;
            if (i + 1 == y) {
                c = i + 2;
            }

            if (c == nums.length) {
                return true;
            }

            if (nums[i] >= nums[c]) {
                return false;
            }
        }

        return true;
    }
}
