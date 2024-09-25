package com.bottomlord.week_110;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-08-17 08:58:16
 */
public class LeetCode_1496_3 {
    public boolean isPathCrossing(String path) {
        int row = 0, col = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);

        char[] cs = path.toCharArray();
        for (char c : cs) {
            if (c == 'N') {
                row--;
            } else if (c == 'S') {
                row++;
            } else if (c == 'E') {
                col++;
            } else {
                col--;
            }

            Integer position = row * 10000 + col;
            if (set.contains(position)) {
                return true;
            }

            set.add(position);
        }

        return false;
    }
}
