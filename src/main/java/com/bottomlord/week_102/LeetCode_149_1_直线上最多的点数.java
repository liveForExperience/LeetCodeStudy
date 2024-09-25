package com.bottomlord.week_102;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/6/24 8:39
 */
public class LeetCode_149_1_直线上最多的点数 {
    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len < 3) {
            return len;
        }

        int ans = 2;
        for (int i = 0; i < len; i++) {
            Map<Double, Integer> map = new HashMap<>();
            int max = 0;
            for (int j = i + 1; j < len; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];

                Double num = y == 0 ? Double.POSITIVE_INFINITY : x == 0 ? 0 : 1D * x / y;
                map.put(num, map.getOrDefault(num, 0) + 1);

                max = Math.max(max, map.get(num));
            }

            ans = Math.max(max + 1, ans);
        }

        return ans;
    }
}
