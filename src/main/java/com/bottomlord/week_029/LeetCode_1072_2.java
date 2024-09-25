package com.bottomlord.week_029;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/1/25 19:46
 */
public class LeetCode_1072_2 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            if (row[0] == 1) {
                for (int i = 0; i < row.length; i++) {
                    row[i] ^= 1;
                }
            }

            int h = Arrays.hashCode(row);
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        return Collections.max(map.values());
    }
}