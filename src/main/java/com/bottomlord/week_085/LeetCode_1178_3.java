package com.bottomlord.week_085;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/2/26 12:49
 */
public class LeetCode_1178_3 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }

            if (Integer.bitCount(mask) <= 7) {
                map.put(mask, map.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int mask = 0;
            for (int i = 1; i < puzzle.length(); i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }

            int sub = mask, count = 0;
            do {
                int tmp = sub | (1 << (puzzle.charAt(0) - 'a'));
                if (map.containsKey(tmp)) {
                    count += map.get(tmp);
                }
                sub = mask & (sub - 1);
            } while (sub != mask);

            ans.add(count);
        }

        return ans;
    }
}
