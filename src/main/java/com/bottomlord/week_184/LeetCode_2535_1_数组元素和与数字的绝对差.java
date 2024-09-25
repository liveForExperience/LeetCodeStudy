package com.bottomlord.week_184;

/**
 * @author chen yue
 * @date 2023-01-19 10:00:14
 */
public class LeetCode_2535_1_数组元素和与数字的绝对差 {
    public int differenceOfSum(int[] nums) {
        int es = 0, ns = 0;
        for (int num : nums) {
            es += num;

            while (num > 0) {
                ns += num % 10;
                num /= 10;
            }
        }

        return Math.abs(es - ns);
    }
}
