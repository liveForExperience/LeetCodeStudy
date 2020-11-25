package com.bottomlord.week_072;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/11/25 10:38
 */
public class LeetCode_356_1_直线镜像 {
    public boolean isReflected(int[][] points) {
        if (points.length == 0) {
            return true;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
        }

        double mid = (max + min) / 2D;

        Map<Double, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            Set<Integer> set = map.getOrDefault((double) point[0], new HashSet<>());
            set.add(point[1]);
            map.put((double) point[0], set);
        }

        for (int[] point : points) {
            double target = mid * 2 - point[0];

            if (!map.containsKey(target)) {
                return false;
            }

            if (!map.get(target).contains(point[1])) {
                return false;
            }
        }

        return true;
    }
}