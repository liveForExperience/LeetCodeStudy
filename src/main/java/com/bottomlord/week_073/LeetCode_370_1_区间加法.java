package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/12/3 8:42
 */
public class LeetCode_370_1_区间加法 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        for (int[] update : updates) {
            int start = update[0], end = update[1];
            for (int i = start; i <= end; i++) {
                ans[i] += update[2];
            }
        }
        return ans;
    }
}
