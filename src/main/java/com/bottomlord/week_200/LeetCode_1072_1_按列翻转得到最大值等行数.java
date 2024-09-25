package com.bottomlord.week_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-05-15 22:00:15
 */
public class LeetCode_1072_1_按列翻转得到最大值等行数 {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        int n = matrix[0].length;

        for (int[] arr : matrix) {
            char[] cs = new char[n];
            for (int j = 0; j < n; j++) {
                cs[j] = (char) ('0' + (arr[0] ^ arr[j]));
            }
            String key = new String(cs);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max;
    }
}
