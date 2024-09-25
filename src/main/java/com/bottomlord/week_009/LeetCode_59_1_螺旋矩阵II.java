package com.bottomlord.week_009;

public class LeetCode_59_1_螺旋矩阵II {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int index = 1, max = n * n, start = 0, end = n - 1;

        while (start <= end) {
            if (start == end) {
                ans[start][end] = n * n;
                break;
            }

            int len = end - start;
            for (int i = 0; i < end - start; i++) {
                ans[start][start + i] = index;
                ans[start + i][end] = index + len;
                ans[end][end - i] = index + len * 2;
                ans[end - i][start] = index + len * 3;
                index++;
            }
            index = index + 3 * len;

            start++;
            end--;
        }

        return ans;
    }
}
