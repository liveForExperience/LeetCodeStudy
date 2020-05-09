package com.bottomlord.week_044;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/5/9 8:46
 */
public class Interview_1711_3 {
    public int findClosest(String[] words, String word1, String word2) {
        int len = words.length, i1 = -1, i2 = -1, ans = len;
        for (int i = 0; i < len; i++) {
            if (Objects.equals(words[i], word1)) {
                i1 = i;
                if (i2 >= 0) {
                    ans = Math.min(ans, Math.abs(i2 - i1));
                }
            }

            if (Objects.equals(words[i], word2)) {
                i2 = i;
                if (i1 >= 0) {
                    ans = Math.min(ans, Math.abs(i2 - i1));
                }
            }
        }

        return ans;
    }
}