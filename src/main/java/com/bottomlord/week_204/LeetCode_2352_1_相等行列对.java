package com.bottomlord.week_204;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-06-06 22:18:05
 */
public class LeetCode_2352_1_相等行列对 {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> colMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            colMap.computeIfAbsent(grid[0][i], x -> new ArrayList<>()).add(i);
        }

        int count = 0;
        for (int row = 0; row < n; row++) {
            int start = grid[row][0];
            List<Integer> cols = colMap.get(start);
            if (cols == null) {
                continue;
            }

            for (Integer col : cols) {
                boolean flag = true;
                for (int j = 0; j < n; j++) {
                    if (grid[j][col] != grid[row][j]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    count++;
                }
            }
        }

        return count;
    }
}
