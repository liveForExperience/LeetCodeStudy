package com.bottomlord.week_112;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-09-03 08:59:49
 */
public class LeetCode_1592_2 {
    public String reorderSpaces(String text) {
        int blankNum = 0;
        List<String> words = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                blankNum++;
                continue;
            }

            int start = i;
            while (i < text.length() && text.charAt(i) != ' ') {
                i++;
            }
            words.add(text.substring(start, i--));
        }

        if (words.isEmpty()) {
            return text;
        }

        int blankGapLen = words.size() == 1 ? 0 : blankNum / (words.size() - 1);

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (blankNum >= blankGapLen) {
                for (int j = 0; j < blankGapLen; j++) {
                    sb.append(" ");
                    blankNum--;
                }
            }
        }

        while (blankNum-- > 0) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
