package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/26 8:24
 */
public class LeetCode_243_1_最短的单词距离 {
    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> i1 = new ArrayList<>(), i2 = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (Objects.equals(word1, words[i])) {
                i1.add(i);
            } else if (Objects.equals(word2, words[i])) {
                i2.add(i);
            }
        }

        int ans = words.length;
        for (Integer a : i1) {
            for (Integer b : i2) {
                ans = Math.min(ans, Math.abs(a - b));
            }
        }

        return ans;
    }
}
