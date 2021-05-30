package com.bottomlord.week_098;

public class LeetCode_LCP22_1_黑白方格画 {
    public int paintingPlan(int n, int k) {
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i * n + j * n - i * j == k) {
                    ans += compute(n, i) * compute(n, j);
                }
            }
        }

        return ans;
    }

    private int compute(int n, int c) {
        if (c == 0 || c == n) {
            return 1;
        }

        return doCompute(n) / doCompute(c) / doCompute(n - c);
    }

    private int doCompute(int c) {
        int count = 1;
        for (int i = 1; i <= c; i++) {
            count *= i;
        }
        return count;
    }
}
