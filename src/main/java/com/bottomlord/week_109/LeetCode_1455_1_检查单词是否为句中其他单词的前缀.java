package com.bottomlord.week_109;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2021-08-15 14:47:14
 */
public class LeetCode_1455_1_检查单词是否为句中其他单词的前缀 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        int index = 1;
        for (String word : arr) {
            if (word.startsWith(searchWord)) {
                return index;
            }
            index++;
        }

        return -1;
    }
}
