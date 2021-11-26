package com.bottomlord.week_124;

/**
 * @author chen yue
 * @date 2021-11-26 09:00:59
 */
public class LeetCode_1880_1_检查某单词是否等于两单词之和 {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return convert(firstWord) + convert(secondWord) == convert(targetWord);
    }

    private long convert(String word) {
        long sum = 0;
        char[] cs = word.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            sum = sum * 10 + (cs[i] - 'a');
        }
        return sum;
    }
}
