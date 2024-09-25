package com.bottomlord.week_006;

public class LeetCode_997_2 {
    public int findJudge(int N, int[][] trust) {
        int[] ans = new int[N];
        for (int[] arr : trust) {
            ans[arr[0] - 1]--;
            ans[arr[1] - 1]++;
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == N - 1) {
                return i + 1;
            }
        }

        return -1;
    }
}