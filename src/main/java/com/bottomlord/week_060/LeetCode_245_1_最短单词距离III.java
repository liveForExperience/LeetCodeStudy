package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/26 8:57
 */
public class LeetCode_245_1_最短单词距离III {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (Objects.equals(word, word1)) {
                l1.add(i);
            }

            if (Objects.equals(word, word2)) {
                l2.add(i);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (Integer i1 : l1) {
            for (Integer i2 : l2) {
                if (Objects.equals(i1, i2)) {
                    continue;
                }

                ans = Math.min(ans, Math.abs(i1 - i2));
            }
        }

        return ans;
    }
}
