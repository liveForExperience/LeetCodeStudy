package com.bottomlord.week_232;

import java.util.List;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2023-12-20 09:02:12
 */
public class LeetCode_2828_1_判别首字母缩略词 {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word == null || word.isEmpty()) {
                continue;
            }

            sb.append(word.charAt(0));
        }

        return Objects.equals(s, sb.toString());
    }
}
