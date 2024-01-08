package com.bottomlord.week_235;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2024-01-08 09:29:51
 */
public class LeetCode_447_1_回旋镖的数量 {
    public int numberOfBoomerangs(int[][] points) {
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            int[] x = points[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                int[] y = points[j];
                int distance = distance(x, y);

                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int value = entry.getValue();
                if (value < 2) {
                    continue;
                }

                sum += value * (value - 1);
            }
        }

        return sum;
    }

    private int distance(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
