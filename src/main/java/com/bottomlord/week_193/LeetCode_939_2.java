package com.bottomlord.week_193;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-03-21 09:51:14
 */
public class LeetCode_939_2 {

    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> colMap = new TreeMap<>();
        for (int[] point : points) {
            colMap.computeIfAbsent(point[0], x -> new ArrayList<>()).add(point[1]);
        }

        Map<Integer, Integer> lastX = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (Integer row : colMap.keySet()) {
            List<Integer> cols = colMap.get(row);
            Collections.sort(cols);
            for (int i = 0; i < cols.size(); i++) {
                for (int j = i + 1; j < cols.size(); j++) {
                    int c1 = cols.get(i), c2 = cols.get(j);
                    int key = c1 * 40001 + c2;
                    if (lastX.containsKey(key)) {
                        min = Math.min(min, (row - lastX.get(key)) * (c2 - c1));
                    }
                    lastX.put(key, row);
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
