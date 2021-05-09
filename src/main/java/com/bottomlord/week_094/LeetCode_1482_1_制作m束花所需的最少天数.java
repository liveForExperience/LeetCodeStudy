package com.bottomlord.week_094;

import java.util.Arrays;

public class LeetCode_1482_1_制作m束花所需的最少天数 {
    private int ans = Integer.MAX_VALUE;

    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (len < m * k) {
            return -1;
        }

        int[] bucket = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            bucket[i] = Arrays.stream(Arrays.copyOfRange(bloomDay, i, i + k)).max().getAsInt();
        }

        dfs(bucket, 0, Integer.MIN_VALUE, 0, m, k);

        return ans;
    }

    private void dfs(int[] bucket, int index, int max, int count, int m, int k) {
        if (count == m) {
            ans = Math.min(ans, max);
            return;
        }

        if (max > ans) {
            return;
        }

        if (index >= bucket.length) {
            return;
        }

        for (int i = index; i < bucket.length; i++) {
            dfs(bucket, i + k, Math.max(max, bucket[i]), count + 1, m, k);
        }
    }
}
