package com.bottomlord.week_112;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chen yue
 * @date 2021-09-02 08:56:19
 */
public class LeetCode_1592_1_重新排列单词间的空格 {
    public String reorderSpaces(String text) {
        String[] splits = text.split(" ");
        List<String> list = Arrays.stream(splits)
                .filter(str -> !Objects.equals(str, " ") && !Objects.equals("", str))
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            return text;
        }

        int len = list.stream().map(String::length).reduce(0, Integer::sum);
        int blankTotalLen = text.length() - len;

        int blankLen = list.size() == 1 ? 0 : blankTotalLen / (list.size() - 1);

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
            for (int i = 0; i < blankLen && blankTotalLen > 0; i++) {
                sb.append(" ");
                blankTotalLen--;
            }
        }

        for (int i = 0; i < blankTotalLen; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
