package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/3 8:21
 */
public class Interview_1001_2 {
    public void merge(int[] A, int m, int[] B, int n) {
        int[] ans = new int[m + n];
        int a = 0, b = 0, index = 0;

        while (a < m || b < n) {
            if (a >= m) {
                while (b < n) {
                    ans[index++] = B[b++];
                }
                break;
            }

            if (b >= n) {
                while (a < m) {
                    ans[index++] = A[a++];
                }
                break;
            }

            if (A[a] <= B[b]) {
                ans[index++] = A[a++];
            } else {
                ans[index++] = B[b++];
            }
        }

        System.arraycopy(ans, 0, A, 0, ans.length);
    }
}