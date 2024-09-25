package com.bottomlord.week_265;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chen yue
 * @date 2024-08-03 14:31:27
 */
public class LeetCode_3143_1_正方形中的最多点数 {
    public int maxPointsInsideSquare(int[][] points, String s) {
        boolean[] memo = new boolean[26];
        TreeMap<Integer, List<Character>> map = new TreeMap<>();
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            int len = Math.max(point[0], point[1]);
            map.computeIfAbsent(len, x -> new ArrayList<>()).add(s.charAt(i));
        }

        int sum = 0;
        for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {
            List<Character> list = entry.getValue();
            boolean flag = false;
            int cnt = 0;
            for (Character c : list) {
                if (memo[c - 'a']) {
                    flag = true;
                    break;
                }

                memo[c - 'a'] = true;
                cnt++;
            }

            if (!flag) {
                sum += cnt;
            } else {
                break;
            }
        }

        return sum;
    }
}
