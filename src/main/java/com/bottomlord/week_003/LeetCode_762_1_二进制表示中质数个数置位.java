package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/22 16:22
 */
public class LeetCode_762_1_二进制表示中质数个数置位 {
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for (int i = L; i <= R; i++) {
            int count = 0;
            int num = i;
            while (num > 0) {
                num = num & (num - 1);
                count++;
            }

            if (isPrime(count)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        if (num < 2 || num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
