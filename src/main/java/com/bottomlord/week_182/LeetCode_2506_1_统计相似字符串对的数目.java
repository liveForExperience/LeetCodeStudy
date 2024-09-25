package com.bottomlord.week_182;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-01-08 06:48:07
 */
public class LeetCode_2506_1_统计相似字符串对的数目 {
    public int similarPairs(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int bit = 0;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                bit |= (1 << (c - 'a'));
            }
            map.put(bit, map.getOrDefault(bit, 0) + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            for (int i = count - 1; i >= 1; i--) {
                ans += i;
            }
        }

        return ans;
    }
}
