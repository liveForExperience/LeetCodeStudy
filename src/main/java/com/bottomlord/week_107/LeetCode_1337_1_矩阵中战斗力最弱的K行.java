package com.bottomlord.week_107;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode_1337_1_矩阵中战斗力最弱的K行 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int len = mat.length;
        int[][] bucket = new int[len][2];
        for (int i = 0; i < len; i++) {
            int[] arr = mat[i];
            if (arr[arr.length - 1] == 1) {
                bucket[i][0] = arr.length;
            } else if (arr[0] == 0) {
                bucket[i][0] = 0;
            } else {
                int count = 0;
                for (int num : arr) {
                    if (num == 0) {
                        break;
                    }
                    count++;
                }
                bucket[i][0] = count;
            }
            bucket[i][1] = i;
        }

        Arrays.sort(bucket, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = bucket[i][0];
        }

        return ans;
    }
}
