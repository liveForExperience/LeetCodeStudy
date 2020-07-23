package com.bottomlord.week_055;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/7/23 8:18
 */
public class LeetCode_149_2 {
    public int maxPoints(int[][] points) {
        int len = points.length;

        if (len < 3) {
            return len;
        }

        int index = 0;
        for (; index < len - 1; index++) {
            if (points[index][0] != points[index + 1][0] || points[index][1] != points[index + 1][1]) {
                break;
            }
        }

        if (index == len - 1) {
            return len;
        }

        int ans = 0;

        for (int i = 0; i < len; i++) {
            int dup = 0, max = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < len; j++) {
                int x = points[i][0] - points[j][0],
                    y = points[i][1] - points[j][1];

                if (x == 0 && y == 0) {
                    dup++;
                    continue;
                }

                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;

                String key = x + ":" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);

                max = Math.max(max, map.get(key));
            }

             ans = Math.max(ans, max + dup + 1);
        }

        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
