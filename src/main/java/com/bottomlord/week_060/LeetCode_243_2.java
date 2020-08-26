package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/26 8:45
 */
public class LeetCode_243_2 {
    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (Objects.equals(word, word1)) {
                l1.add(i);
            } else if (Objects.equals(word, word2)) {
                l2.add(i);
            }
        }

        int i1 = 0, i2 = 0, ans = Integer.MAX_VALUE;
        while (i1 < l1.size() && i2 < l2.size()) {
            ans = Math.min(ans, Math.abs(l1.get(i1) - l2.get(i2)));

            if (l1.get(i1) > l2.get(i2)) {
                i2++;
            } else {
                i1++;
            }
        }

        return ans;
    }
}