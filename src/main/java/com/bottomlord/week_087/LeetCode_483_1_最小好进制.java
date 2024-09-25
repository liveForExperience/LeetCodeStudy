package com.bottomlord.week_087;

/**
 * @author ChenYue
 * @date 2021/3/9 8:27
 */
public class LeetCode_483_1_最小好进制 {
    public String smallestGoodBase(String n) {
        long target = Long.parseLong(n);
        for (int m = 59; m > 1; m--) {
            long k = (long) Math.pow(target, 1.0 / m);

            if (k <= 1) {
                continue;
            }

            long s = 0;
            for (int i = 0; i <= m; i++) {
                s = s * k + 1;
            }

            if (s == target) {
                return String.valueOf(k);
            }
        }

        return String.valueOf(target - 1);
    }
}
