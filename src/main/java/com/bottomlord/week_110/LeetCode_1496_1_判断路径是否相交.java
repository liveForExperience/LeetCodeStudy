package com.bottomlord.week_110;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-08-17 08:47:17
 */
public class LeetCode_1496_1_判断路径是否相交 {
    public boolean isPathCrossing(String path) {
        int row = 0, col = 0;
        Set<String> set = new HashSet<>();
        set.add(row + ":" + col);

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

            String position = row + ":" + col;
            if (set.contains(position)) {
                return true;
            }

            set.add(position);
        }

        return false;
    }
}
