package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-10-03 19:26:13
 */
public class LeetCode_1752_1_检查数组是否经旋转和轮转得到 {
    public boolean check(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                index = i;
            }
        }

        int pre = min;
        for (int i = (index + 1) % n; i != index; i = (i + 1) % n) {
            if (nums[i] < pre && (nums[i] != min || nums[(i + 1) % n] != min)) {
                return false;
            }

            pre = nums[i];
        }

        return true;
    }
}
