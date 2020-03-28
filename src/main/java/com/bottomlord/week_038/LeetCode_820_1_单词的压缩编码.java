package com.bottomlord.week_038;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/3/28 15:20
 */
public class LeetCode_820_1_单词的压缩编码 {
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }

        int ans = 0;
        for (String word : set) {
            ans += word.length() + 1;
        }
        return ans;
    }
}
