package com.bottomlord.week_096;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LeetCode_13_1_罗马数字转整数 {
    public int romanToInt(String s) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("CM", 900);
        map.put("CD", 400);
        map.put("XC", 90);
        map.put("XL", 40);
        map.put("IX", 9);
        map.put("IV", 4);
        map.put("M", 1000);
        map.put("D", 500);
        map.put("C", 100);
        map.put("L", 50);
        map.put("X", 10);
        map.put("V", 5);
        map.put("I", 1);

        boolean[] bucket = new boolean[s.length()];

        int ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            if (Objects.equals(key, "M")) {
                break;
            }

            int index = s.indexOf(entry.getKey());
            while (index != -1) {
                bucket[index] = bucket[index + 1] = true;
                ans += map.get(entry.getKey());
                index = s.indexOf(entry.getKey(), index + 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (!bucket[i]) {
                ans += map.get("" + s.charAt(i));
            }
        }

        return ans;
    }
}
