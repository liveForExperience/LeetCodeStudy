package com.bottomlord.week_131;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-11 22:06:53
 */
public class LeetCode_2053_1_数组中第k个独一无二的字符串 {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        int count = 0;
        for (String s : arr) {
            if (map.get(s) == 1) {
                count++;
            }

            if (count == k) {
                return s;
            }
        }

        return "";
    }
}
