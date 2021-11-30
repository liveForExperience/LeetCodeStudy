package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-11-30 08:51:57
 */
public class LeetCode_400_1_第N位数字 {
    public int findNthDigit(int n) {
        int digit = 1;
        while (Math.pow(10, digit - 1) * 9 * digit < n) {
            n -= Math.pow(10, digit - 1) * 9 * digit;
            digit++;
        }

        int num = (n - 1) / digit + (int) Math.pow(10, digit - 1);

        int index = n % digit == 0 ? digit - 1 : n % digit - 1;
        return Integer.toString(num).charAt(index) - '0';
    }
}
