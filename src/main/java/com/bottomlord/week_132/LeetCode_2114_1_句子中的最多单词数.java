package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-22 21:41:07
 */
public class LeetCode_2114_1_句子中的最多单词数 {
    public int mostWordsFound(String[] sentences) {
        int max = 0;
        for (String sentence : sentences) {
            max = Math.max(max, count(sentence));
        }
        return max;
    }

    private int count(String sentence) {
        int count = 0;
        for (char c : sentence.toCharArray()) {
            if (c == ' ') {
                count++;
            }
        }
        return count + 1;
    }
}
