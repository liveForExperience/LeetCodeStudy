package com.bottomlord.week_009;

public class LeetCode_204_1_计数质数 {
    public int countPrimes(int n) {
        boolean[] bucket = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!bucket[i]) {
                count++;
            }

            for (int j = 2; i * j < n; j++) {
                bucket[j * i] = true;
            }
        }
        return count;
    }
}
