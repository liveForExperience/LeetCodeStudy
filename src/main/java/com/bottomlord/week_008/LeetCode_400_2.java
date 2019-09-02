package com.bottomlord.week_008;

public class LeetCode_400_2 {
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