package com.bottomlord.week_197;

/**
 * @author chen yue
 * @date 2023-04-21 22:59:36
 */
public class LeetCode_2639_1_查询网格图中每一列的宽度 {
    public int[] findColumnWidth(int[][] grid) {
        int c = grid[0].length;
        int[] ans = new int[c];
        for (int[] arr : grid) {
            for (int j = 0; j < c; j++) {
                ans[j] = Math.max(ans[j], Integer.toString(arr[j]).length());
            }
        }
        return ans;
    }
}
