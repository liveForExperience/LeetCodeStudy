package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-03-01 09:07:34
 */
public class LeetCode_1326_1_灌溉花园的最少龙头数目 {
    public int minTaps(int n, int[] ranges) {
        int[] rights = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int l = Math.max(i - ranges[i], 0);
            rights[l] = Math.max(rights[l], i + ranges[i]);
        }

        int l = 0, r = 0, idx = 0, ans = 0;
        while (idx <= n && r < n) {
            if (idx <= l) {
                r = Math.max(r, rights[idx]);
                idx++;
            } else {
                if (idx > r) {
                    return -1;
                } else {
                    l = r;
                    ans++;
                }
            }
        }

        return ans;
    }
}
