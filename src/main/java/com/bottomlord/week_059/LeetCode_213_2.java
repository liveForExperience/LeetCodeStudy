package com.bottomlord.week_059;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/8/18 8:23
 */
public class LeetCode_213_2 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
                        doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }

        return cur;
    }
}
