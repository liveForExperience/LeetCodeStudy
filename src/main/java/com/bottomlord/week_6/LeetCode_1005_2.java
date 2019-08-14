package com.bottomlord.week_6;

public class LeetCode_1005_2 {
    public int largestSumAfterKNegations(int[] A, int K) {
        int[] bucket = new int[201];
        int sum = 0;
        for (int num : A) {
            bucket[num + 100]++;
        }

        int i = 0;
        while (K != 0 && i < 100) {
            while (K != 0 && bucket[i] != 0) {
                bucket[i]--;
                bucket[200 - i]++;
                K--;
            }

            i++;

            if (K == 0) {
                break;
            }
        }

        if (K != 0 && bucket[100] != 0) {
            K = 0;
        }

        if (K % 2 != 0) {
            while (bucket[i] == 0) {
                i++;
            }

            if (i == 201) {
                return 0;
            }

            bucket[i]--;
            sum += -(i - 100);
        }

        for (i = 0; i < 201; i++) {
            sum += bucket[i] * (i - 100);
        }

        return sum;
    }
}