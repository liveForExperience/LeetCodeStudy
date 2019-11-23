package com.bottomlord.week_020;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_781_1_森林中的兔子 {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (key == 0) {
                ans += map.get(key);
            } else {
                int val = map.get(key);
                int num = val % (key + 1);
                ans += num == 0 ? val : val + key + 1 - num;
            }
        }

        return ans;
    }
}
