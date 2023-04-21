package com.bottomlord.week_197;

/**
 * @author chen yue
 * @date 2023-04-21 23:05:34
 */
public class LeetCode_2643_1_一最多的行 {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[]{0, 0};
        for (int i = 0; i < mat.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    cnt++;
                }
            }

            if (cnt > ans[1]) {
                ans[0] = i;
                ans[1] = cnt;
            }
        }

        return ans;
    }
}
