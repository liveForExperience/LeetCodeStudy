package com.bottomlord.week_096;

/**
 * @author ChenYue
 * @date 2021/5/12 9:03
 */
public class LeetCode_1310_1_子数组异或查询 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int len = arr.length, ansLen = queries.length;
        int[] xors = new int[len];
        xors[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xors[i] = xors[i - 1] ^ arr[i];
        }

        int[] ans = new int[ansLen];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0], end = queries[i][1];
            ans[i] = (start == 0 ? 0 : xors[start - 1]) ^ xors[end];
        }

        return ans;
    }
}
