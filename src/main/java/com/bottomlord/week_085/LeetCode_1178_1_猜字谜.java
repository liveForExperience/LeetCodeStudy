package com.bottomlord.week_085;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/2/26 8:45
 */
public class LeetCode_1178_1_猜字谜 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < puzzles.length; i++) {
            boolean[] dict = new boolean[26];
            for (char c : puzzles[i].toCharArray()) {
                dict[c - 'a'] = true;
            }
            int count = 0;
            for (String word : words) {
                boolean matchHead = false, matchAll = true;
                for (char c : word.toCharArray()) {
                    if (c == puzzles[i].charAt(0)) {
                        matchHead = true;
                    }

                    if (!dict[c - 'a']) {
                        matchAll = false;
                        break;
                    }
                }

                if (matchAll && matchHead) {
                    count++;
                }
            }

            ans.add(count);
        }

        return ans;
    }
}
