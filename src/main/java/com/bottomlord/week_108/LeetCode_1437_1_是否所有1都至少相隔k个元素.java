package com.bottomlord.week_108;

/**
 * @author chen yue
 * @date 2021-08-08 16:04:21
 */
public class LeetCode_1437_1_是否所有1都至少相隔k个元素 {
    public boolean kLengthApart(int[] nums, int k) {
        int idx = nums.length, dis = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                dis++;
            } else {
                if (idx == nums.length) {
                    idx = i;
                } else {
                    if (dis < k) {
                        return false;
                    }
                }
                dis = 0;
            }
        }

        return true;
    }
}
