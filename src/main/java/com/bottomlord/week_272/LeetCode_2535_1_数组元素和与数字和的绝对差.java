package com.bottomlord.week_272;

/**
 * @author chen yue
 * @date 2024-09-26 08:57:54
 */
public class LeetCode_2535_1_数组元素和与数字和的绝对差 {
    public int differenceOfSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += (num - sumDigit(num));
        }
        return Math.abs(sum);
    }

    private int sumDigit(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
