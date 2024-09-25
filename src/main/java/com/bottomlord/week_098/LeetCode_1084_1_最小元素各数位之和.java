package com.bottomlord.week_098;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/27 8:22
 */
public class LeetCode_1084_1_最小元素各数位之和 {
    public int sumOfDigits(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = Arrays.stream(nums).min().getAsInt(), ans = 0;
        while (min != 0) {
            int num = min % 10;
            min /= 10;
            ans += num;
        }

        return ans % 2 == 1 ? 0 : 1;
    }
}
