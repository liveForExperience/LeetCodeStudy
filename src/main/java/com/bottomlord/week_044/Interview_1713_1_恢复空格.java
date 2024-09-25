package com.bottomlord.week_044;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/5/10 15:16
 */
public class Interview_1713_1_恢复空格 {
    public int respace(String[] dictionary, String sentence) {
        int[] cache = new int[sentence.length()];
        Arrays.fill(cache, -1);
        return backTrack(dictionary, 0, sentence, cache);
    }

    private int backTrack(String[] dictionary, int start, String sentence, int[] cache) {
        if (start > sentence.length()) {
            return 0;
        }

        if (cache[start] != -1) {
            return cache[start];
        }

        int min = sentence.length() - start;
        for (String word : dictionary) {
            int index = sentence.indexOf(word, start);

            if (index != -1) {
                min = Math.min(min, index - start + backTrack(dictionary, index + word.length(), sentence, cache));
            }
        }

        return cache[start] = min;
    }
}
