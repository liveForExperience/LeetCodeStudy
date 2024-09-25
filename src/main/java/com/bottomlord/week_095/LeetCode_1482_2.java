package com.bottomlord.week_095;

import java.util.Arrays;

public class LeetCode_1482_2 {
    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (m * k > len) {
            return -1;
        }

        int l = 0, r = Arrays.stream(bloomDay).max().getAsInt();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (match(bloomDay, m, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean match(int[] bloomDay, int m, int k, int limit) {
        int flowers = 0, branch = 0, len = bloomDay.length;

        for (int i = 0; i < len && branch < m; i++) {
            if (bloomDay[i] <= limit) {
                flowers++;
                if (flowers == k) {
                    branch++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }

        return branch >= m;
    }
}
