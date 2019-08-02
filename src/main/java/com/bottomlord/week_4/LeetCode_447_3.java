package com.bottomlord.week_4;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/8/2 19:11
 */
public class LeetCode_447_3 {
    public int numberOfBoomerangs(int[][] points) {
        Map<Double, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j <points.length; j++) {
                if (i == j) {
                    continue;
                }

                double distance = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                int tmp = map.getOrDefault(distance, 0);
                count += tmp * 2;
                map.put(distance, tmp + 1);
            }

            map.clear();
        }
        return count;
    }
}
