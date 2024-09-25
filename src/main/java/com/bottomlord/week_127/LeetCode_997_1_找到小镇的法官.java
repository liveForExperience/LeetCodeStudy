package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 09:46:33
 */
public class LeetCode_997_1_找到小镇的法官 {
    public int findJudge(int n, int[][] trust) {
        int[] ins = new int[n], outs = new int[n];
        for (int[] arr : trust) {
            outs[arr[0] - 1]++;
            ins[arr[1] - 1]++;
        }

        for (int i = 0; i < n; i++) {
            if (ins[i] == n - 1 && outs[i] == 0) {
                return i + 1;
            }
        }

        return -1;
    }
}
