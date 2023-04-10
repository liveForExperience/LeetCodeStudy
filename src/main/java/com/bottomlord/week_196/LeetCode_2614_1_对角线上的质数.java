package com.bottomlord.week_196;

/**
 * @author chen yue
 * @date 2023-04-10 22:02:02
 */
public class LeetCode_2614_1_对角线上的质数 {

    public int diagonalPrime(int[][] nums) {
        int max = 0;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && n - i - 1 != j) {
                    continue;
                }

                int num = nums[i][j];
                if (isPrime(num)) {
                    max = Math.max(max, num);
                }
            }
        }

        return max;
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
