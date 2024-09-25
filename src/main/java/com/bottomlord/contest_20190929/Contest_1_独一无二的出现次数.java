package com.bottomlord.contest_20190929;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Contest_1_独一无二的出现次数 {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return new HashSet<>(map.values()).size() == map.size();
    }
}
