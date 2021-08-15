package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-15 15:06:19
 */
public class LeetCode_1464_1_数组中两元素的最大乘积 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, sec = Integer.MIN_VALUE;
        for (int num : nums) {
            if (sec == Integer.MIN_VALUE) {
                sec = num;
                continue;
            }

            if (max == Integer.MIN_VALUE) {
                max = Math.max(sec, num);
                sec = Math.min(sec, num);
                continue;
            }

            if (num > max) {
                sec = max;
                max = num;
                continue;
            }

            if (num > sec) {
                sec = num;
            }
        }

        return (max - 1) * (sec - 1);
    }
}
