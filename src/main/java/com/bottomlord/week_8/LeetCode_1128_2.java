package com.bottomlord.week_8;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_1128_2 {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] arr : dominoes) {
            int one = arr[0];
            int two = arr[1];

            int out = Math.min(one, two);
            int in = Math.max(one, two);

            map.put(out, map.getOrDefault(out, new HashMap<>()));
            Map<Integer, Integer> innerMap = map.get(out);
            innerMap.put(in, innerMap.getOrDefault(in, 0) + 1);
        }

        int sum = 0;
        for (Map<Integer, Integer> innerMap : map.values()) {
            for (Integer num : innerMap.values()) {
                sum += num * (num - 1) / 2;
            }
        }
        return sum;
    }
}