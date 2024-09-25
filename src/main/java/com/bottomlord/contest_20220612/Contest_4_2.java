package com.bottomlord.contest_20220612;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-06-12 17:05:56
 */
public class Contest_4_2 {
    public long distinctNames(String[] ideas) {
        Map<String, Integer> map = new HashMap<>();
        for (String idea : ideas) {
            String prefix = idea.substring(1);
            map.put(prefix, map.getOrDefault(prefix, 0) | (1 << idea.charAt(0) - 'a'));
        }

        int[][] count = new int[26][26];
        long ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int mask = entry.getValue();

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (((mask >> i) & 1) == 0) {
                        if (((mask >> j) & 1) > 0) {
                            count[i][j]++;
                        }
                    } else {
                        if (((mask >> j) & 1) == 0) {
                            ans += count[i][j];
                        }
                    }
                }
            }
        }

        return ans * 2;
    }
}
