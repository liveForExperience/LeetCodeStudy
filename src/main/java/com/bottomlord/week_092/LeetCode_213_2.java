package com.bottomlord.week_092;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/4/15 9:00
 */
public class LeetCode_213_2 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        if (len < 3) {
            return Arrays.stream(nums).max().getAsInt();
        }

        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
                        doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int len = nums.length;
        int pre2 = nums[0], pre = Math.max(pre2, nums[1]), cur = 0;
        for (int i = 2; i < len; i++) {
            cur = Math.max(pre2 + nums[i], pre);
            pre2 = pre;
            pre = cur;
        }

        return cur;
    }
}
