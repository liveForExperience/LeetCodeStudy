package com.bottomlord.week_123;

/**
 * @author chen yue
 * @date 2021-11-20 14:50:56
 */
public class LeetCode_1859_1_将句子排序 {
    public String sortSentence(String s) {
        String[] ss = s.split(" ");
        String[] ans = new String[ss.length];
        for (String str : ss) {
            int index = str.charAt(str.length() - 1) - '0';
            ans[index - 1] = str.substring(0, str.length() - 1);
        }

        return String.join(" ", ans);
    }
}
