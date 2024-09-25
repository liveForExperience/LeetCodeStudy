package com.bottomlord.week_058;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/11 8:51
 */
public class LeetCode_187_2 {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = 10, len = s.length();
        if (len < n) {
            return new ArrayList<>();
        }

        Set<Integer> memo = new HashSet<>();
        Set<String> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = map.get(s.charAt(i));
        }

        int num = 0, headNum = (int) Math.pow(4, 10);
        for (int i = 0; i < len - n + 1; i++) {
            if (i != 0) {
                num = num * 4 - headNum * arr[i - 1] + arr[i + n - 1];
            } else {
                for (int j = 0; j < n; j++) {
                    num = num * 4 + arr[j];
                }
            }

            if (memo.contains(num)) {
                set.add(s.substring(i, i + n));
            }
            memo.add(num);
        }

        return new ArrayList<>(set);
    }
}