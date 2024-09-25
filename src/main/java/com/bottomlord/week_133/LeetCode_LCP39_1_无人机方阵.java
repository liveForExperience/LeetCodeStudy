package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-30 15:20:01
 */
public class LeetCode_LCP39_1_无人机方阵 {
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int[] bucket = new int[10001];
        int row = source.length, col = source[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bucket[target[i][j]]++;
                bucket[source[i][j]]--;
            }
        }

        int sum = 0;
        for (int num : bucket) {
            sum += Math.max(num, 0);
        }
        return sum;
    }
}
