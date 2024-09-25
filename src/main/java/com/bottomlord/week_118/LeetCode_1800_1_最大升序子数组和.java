package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-13 23:02:29
 */
public class LeetCode_1800_1_最大升序子数组和 {
    public int maxAscendingSum(int[] nums) {
        int max = 0, pre = -1, sum = 0;
        for (int num : nums) {
            if (num > pre) {
                sum += num;
            } else {
                max = Math.max(max, sum);
                sum = num;
            }

            pre = num;
        }

        return Math.max(max, sum);
    }
}
