package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/2 8:59
 */
public class LeetCode_1175_1_质数排列 {
    public int numPrimeArrangements(int n) {
        boolean[] arr = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                count++;
                for (int j = 2; i * j <= n; j++) {
                    arr[i * j] = true;
                }
            }
        }

        return (int)(calculate(count) * calculate(n - count) % 1000000007);
    }

    private long calculate(int count) {
        long ans = 1;
        for (int i = count; i >= 1; i--) {
            ans *= i;
            ans %= 1000000007;
        }
        return ans;
    }
}
