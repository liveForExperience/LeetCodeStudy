package com.bottomlord.week_067;

/**
 * @author ChenYue
 * @date 2020/10/21 18:27
 */
public class LeetCode_311_1_稀疏矩阵的乘法 {
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    ans[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return ans;
    }
}
