package com.bottomlord.week_135;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-02-08 08:50:42
 */
public class LeetCode_1001_1_网格照明 {
    private final int[][] dirs = new int[][]{{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 0}, {1, -1}, {1, 1}};

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<>(), col = new HashMap<>(),
                left = new HashMap<>(), right = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int[] lamp : lamps) {
            int x = lamp[0], y = lamp[1],
                    l = x + y, r = x - y;

            if (set.contains(x * n + y)) {
                continue;
            }

            increase(row, x);
            increase(col, y);
            increase(left, l);
            increase(right, r);
            set.add(x * n + y);
        }

        int[] ans = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1],
                    l = x + y, r = x - y;

            if (row.containsKey(x) || col.containsKey(y) || left.containsKey(l) || right.containsKey(r)) {
                ans[index] = 1;
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1],
                    nl = nx + ny, nr = nx - ny;

                if (set.contains(nx * n + ny)) {
                    set.remove(nx * n + ny);
                    decrease(row, nx);
                    decrease(col, ny);
                    decrease(left, nl);
                    decrease(right, nr);
                }
            }

            index++;
        }

        return ans;
    }

    private void increase(Map<Integer, Integer> map, Integer num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private void decrease(Map<Integer, Integer> map, Integer num) {
        Integer value = map.getOrDefault(num, 0);
        if (value <= 1) {
            map.remove(num);
            return;
        }

        map.put(num, map.get(num) - 1);
    }
}
