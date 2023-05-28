package com.bottomlord.week_202;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-05-28 23:16:11
 */
public class LeetCode_1439_1_有序矩阵中的第K个最小数组和 {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] pre = mat[0];
        for (int i = 1; i < m; i++) {
            int[] cur = mat[i];
            int[] tmp = new int[pre.length * cur.length];

            int index = 0;
            for (int x : pre) {
                for (int y : cur) {
                    tmp[index++] = x + y;
                }
            }

            Arrays.sort(tmp);
            if (tmp.length > k) {
                tmp = Arrays.copyOfRange(tmp, 0, k);
            }

            pre = tmp;
        }

        return pre[k - 1];
    }
}
