package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/8/28 8:18
 */
public class LeetCode_245_2 {
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
        int i1 = 0, i2 = 0;
        while (i1 < l1.size() && i2 < l2.size()) {
            Integer num1 = l1.get(i1), num2 = l2.get(i2);
            if (Objects.equals(num1, num2)) {
                i1++;
                continue;
            }

            ans = Math.min(ans, Math.abs(num1 - num2));

            if (num1 > num2) {
                i2++;
            } else {
                i1++;
            }
        }

        return ans;
    }
}
