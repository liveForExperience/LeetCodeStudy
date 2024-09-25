package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/3 15:28
 */
public class LeetCode_7_1_整数反转 {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }

        return rev;
    }
}
