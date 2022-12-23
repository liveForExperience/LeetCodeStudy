package com.bottomlord.week_180;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-12-24 06:58:07
 */
public class LeetCode_2423_1_删除字符使频率相同 {
    public boolean equalFrequency(String word) {
        int[] bucket = new int[26];
        char[] cs = word.toCharArray();
        for (char c : cs) {
            bucket[c - 'a']++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int one = 0;
        for (int num : bucket) {
            if (num == 0) {
                continue;
            }

            if (num == 1) {
                one++;
            }

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        if (map.size() > 2) {
            return false;
        }

        if (map.size() == 1) {
            if (one > 0) {
                return true;
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                return entry.getValue() == 1;
            }
        }

        one = 0;

        int x = 0, xn = 0, y = 0, yn = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                one = entry.getValue();
            }

            if (x == 0) {
                x = entry.getKey();
                xn = entry.getValue();
            } else {
                y = entry.getKey();
                yn = entry.getValue();
            }
        }

        if (one == 1) {
            return true;
        }

        if (Math.abs(x - y) != 1) {
            return false;
        }

        if (x > y) {
            return xn == 1;
        } else {
            return yn == 1;
        }
    }
}
