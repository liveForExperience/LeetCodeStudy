package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/3/1 18:19
 */
public class Interview_44_1_数字序列某一位的数字 {
    public int findNthDigit(int n) {
        int digit = 1;
        while (n > digit * Math.pow(10, digit - 1) * 9) {
            n -= digit * Math.pow(10, digit - 1) * 9;
            digit++;
        }

        int num = (n - 1) / digit + (int)Math.pow(10, digit - 1);

        int index = n % digit == 0 ? digit - 1 : n % digit - 1;
        return Integer.toString(num).charAt(index) - '0';
    }
}
