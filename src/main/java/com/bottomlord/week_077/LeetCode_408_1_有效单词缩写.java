package com.bottomlord.week_077;

/**
 * @author ChenYue
 * @date 2020/12/30 9:02
 */
public class LeetCode_408_1_有效单词缩写 {
    public boolean validWordAbbreviation(String word, String abbr) {
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
