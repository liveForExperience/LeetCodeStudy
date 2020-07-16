package com.bottomlord.week_054;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/7/16 8:28
 */
public class LeetCode_135_2 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len == 0) {
            return 0;
        }

        int[] l2r = new int[len],
              r2l = new int[len];

        Arrays.fill(l2r, 1);
        Arrays.fill(r2l, 1);

        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                l2r[i] = l2r[i - 1] + 1;
            }
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                r2l[i] = r2l[i + 1] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.max(l2r[i], r2l[i]);
        }

        return ans;
    }
}