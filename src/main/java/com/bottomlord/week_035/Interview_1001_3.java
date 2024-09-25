package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/3 8:30
 */
public class Interview_1001_3 {
    public void merge(int[] A, int m, int[] B, int n) {
        int index = m + n - 1, a = m - 1, b = n - 1;
        while (a >= 0 || b >= 0) {
            if (a < 0) {
                while (b >= 0) {
                    A[index--] = B[b--];
                }
                break;
            }

            if (b < 0) {
                while (a >= 0) {
                    A[index--] = A[a--];
                }
                break;
            }

            if (A[a] >= B[b]) {
                A[index--] = A[a--];
            } else {
                A[index--] = B[b--];
            }
        }
    }
}
