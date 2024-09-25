package com.bottomlord.week_248;

/**
 * @author chen yue
 * @date 2024-04-13 14:17:24
 */
public class LeetCode_2924_1_找到冠军II {
    public int findChampion(int n, int[][] edges) {
        int[] cnt = new int[n];
        for (int[] edge : edges) {
            cnt[edge[1]]++;
        }

        int ans = -1;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == 0) {
                if (ans != -1) {
                    return -1;
                }

                ans = i;
            }
        }

        return ans;
    }
}
