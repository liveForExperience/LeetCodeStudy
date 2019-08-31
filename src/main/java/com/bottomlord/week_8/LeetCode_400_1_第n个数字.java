package com.bottomlord.week_8;

public class LeetCode_400_1_第n个数字 {
    public int findNthDigit(int n) {
        double dn = n;
        int k = 0, count = 0;
        while (dn > 0) {
            dn = dn - 9 * Math.pow(10, ++k - 1) * k;
        }
        dn += 9 * Math.pow(10, k - 1) * k;
        while (dn > 0) {
            dn -= k;
            count++;
        }
        double num = Math.pow(10, k - 1) + count - 1;
        while (dn++ < 0) {
            num /= 10;
        }
        return (int)num % 10;
    }
}
