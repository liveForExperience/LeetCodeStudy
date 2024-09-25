package com.bottomlord.week_077;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/30 13:33
 */
public class LeetCode_411_1_最短特异单词缩写 {
    public String minAbbreviation(String target, String[] dictionary) {
        List<String> list = new ArrayList<>();
        backTrack(target, 0, 0, new StringBuilder(), list);
        list.sort(Comparator.comparingInt(String::length));
        for (String abbr : list) {
            boolean isAbbr = false;
            for (String word : dictionary) {
                if (isAbbr(word, abbr)) {
                    isAbbr = true;
                    break;
                }
            }

            if (!isAbbr) {
                return abbr;
            }
        }

        return target;
    }

    private void backTrack(String word, int index, int num, StringBuilder sb, List<String> list) {
        int len = sb.length();

        if (index == word.length()) {
            if (num != 0) {
                sb.append(num);
            }
            list.add(sb.toString());
        } else {
            backTrack(word, index + 1, num + 1, sb, list);

            if (num != 0) {
                sb.append(num);
            }

            sb.append(word.charAt(index));
            backTrack(word, index + 1, 0, sb, list);
        }

        sb.setLength(len);
    }

    private boolean isAbbr(String word, String abbr) {
        int wIndex = 0, aIndex = 0, wLen = word.length(), aLen = abbr.length();
        while (wIndex < wLen && aIndex < aLen) {
            if (word.charAt(wIndex) == abbr.charAt(aIndex)) {
                wIndex++;
                aIndex++;
                continue;
            }

            if (!Character.isDigit(abbr.charAt(aIndex))) {
                return false;
            }

            StringBuilder numSb = new StringBuilder();
            while (aIndex < aLen && Character.isDigit(abbr.charAt(aIndex))) {
                numSb.append(abbr.charAt(aIndex++));
            }

            if (numSb.charAt(0) == '0') {
                return false;
            }

            int num = Integer.parseInt(numSb.toString());
            wIndex += num;
        }

        return wIndex == wLen && aIndex == aLen;
    }
}
