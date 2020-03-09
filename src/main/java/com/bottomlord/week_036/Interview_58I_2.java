package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/9 12:33
 */
public class Interview_58I_2 {
    public String reverseWords(String s) {
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