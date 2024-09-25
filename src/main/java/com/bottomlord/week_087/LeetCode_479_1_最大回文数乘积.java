package com.bottomlord.week_087;

/**
 * @author ChenYue
 * @date 2021/3/8 8:31
 */
public class LeetCode_479_1_最大回文数乘积 {
    public int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }

        long up = (long)Math.pow(10, n) - 1, low = up / 10 + 1,
             max = up * up, half = (long) (max / Math.pow(10, n));

        while (true) {
            long cur = get(half);


            for (long i = up; i >= low; i--) {
                if (i * i < cur) {
                    break;
                }

                if (cur % i == 0) {
                    return (int)(cur % 1337);
                }
            }

            half--;
        }
    }

    private long get(long num) {
        String numStr = String.valueOf(num);
        return Long.parseLong(numStr + new StringBuilder(numStr).reverse().toString());
    }
}
