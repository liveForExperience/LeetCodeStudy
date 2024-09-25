package com.bottomlord.week_029;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/1/25 15:14
 */
public class LeetCode_1072_1_按列翻转得到最大值等行数 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        for (int[] row : matrix) {
            if (row[0] == 0) {
                continue;
            }

            for (int i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            StringBuilder sb = new StringBuilder();
            for (int num : row) {
                sb.append(num);
            }
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }

        return Collections.max(map.values());
    }
}
