package com.bottomlord.week_129;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2021-12-27 08:59:23
 */
public class LeetCode_825_2 {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0, n = ages.length;
        for (int i = 0, j = 0, k = 0; k < n; k++) {
            while (i < n && !check(ages[k], ages[i])) {
                i++;
            }
            if (j < k) {
                j = k;
            }

            while (j < n && check(ages[k], ages[j])) {
                j++;
            }

            if (j > i) {
                ans += j - i - 1;
            }
        }

        return ans;
    }

    private boolean check(int x, int y) {
        return (!(y <= 0.5 * x + 7)) && (y <= x);
    }
}
