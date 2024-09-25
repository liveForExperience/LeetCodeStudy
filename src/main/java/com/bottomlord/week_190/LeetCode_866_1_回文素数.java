package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-03-02 08:50:34
 */
public class LeetCode_866_1_回文素数 {
    public int primePalindrome(int n) {
        while (true) {
            if (reverse(n) == n && isPrime(n)) {
                return n;
            }
            n++;

            if (n >= 10_000_000 && n <= 100_000_000) {
                n = 100_000_000;
            }
        }
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int reverse(int num) {
        int reverse = 0;
        while (num > 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        return reverse;
    }
}
