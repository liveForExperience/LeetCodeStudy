package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/11 14:03
 */
public class LeetCode_873_2 {
    public int lenLongestFibSubseq(int[] A) {
        int ans = 0, len = A.length;
        for (int i = 0; i < len - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < len - 1; j++) {
                int sum = A[i] + A[j];
                while (k < len && A[k] < sum) {
                    k++;
                }

                if (k == len) {
                    return ans;
                }

                if (A[k] != sum) {
                    continue;
                }

                int a, b = A[j], count = 2, pos = k;
                while (pos < len && A[pos] == sum) {
                    count++;
                    sum = sum + b;
                    a = b;
                    b = sum - a;

                    while (pos < len && A[pos] < sum) {
                        pos++;
                    }
                }

                ans = Math.max(ans, count);
            }
            if (k == len) {
                break;
            }
        }

        return ans;
    }
}