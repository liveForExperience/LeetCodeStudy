package com.bottomlord.week_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_401_1_二进制手表 {
    public List<String> readBinaryWatch(int num) {
        List<String> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(60);
        map.put(0, 0);
        for (int i = 1; i < 60; i++) {
            int count = 1;
            int j = i;
            while ((j &= (j - 1)) != 0) {
                count++;
            }
            map.put(i, count);
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (map.get(i) + map.get(j) == num) {
                    String min = j < 10 ? "0" + j : "" + j;
                    ans.add("" + i + ":" + min);
                }
            }
        }

        return ans;
    }
}
