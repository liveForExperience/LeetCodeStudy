package com.bottomlord.week_114;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-13 08:28:09
 */
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        Map<Double, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                double distance = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                int count = map.getOrDefault(distance, 0);
                ans += count * 2;
                map.put(distance, count + 1);
            }

            map.clear();
        }

        return ans;
    }
}
