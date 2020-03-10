package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/9 18:52
 */
public class LeetCode_151_1_翻转字符串里的单词 {
    public String reverseWords(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return "";
        }

        String[] ss = s.split(" ");
        if (ss.length == 1) {
            return ss[0];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            if (!"".equals(ss[i])) {
                sb.append(" ").append(ss[i]);
            }
        }

        return sb.deleteCharAt(0).toString();
    }
}
