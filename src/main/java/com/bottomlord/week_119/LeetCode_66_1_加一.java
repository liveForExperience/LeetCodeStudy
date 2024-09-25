package com.bottomlord.week_119;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-10-21 08:46:01
 */
public class LeetCode_66_1_加一 {
    public int[] plusOne(int[] digits) {
        int n = digits.length, carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            int num = digits[i] + carry;
            digits[i] = num % 10;
            if (num / 10 == 0) {
                carry = 0;
                break;
            }
        }

        if (carry == 1) {
            int[] arr = new int[n + 1];
            arr[0] = 1;
            System.arraycopy(digits, 0, arr, 1, n);
            return arr;
        }

        return digits;
    }
}
