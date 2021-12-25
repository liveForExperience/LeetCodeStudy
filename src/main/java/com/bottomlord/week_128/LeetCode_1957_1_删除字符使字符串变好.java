package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-25 19:36:36
 */
public class LeetCode_1957_1_删除字符使字符串变好 {
    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = 0;
            while (i + 1 < s.length() && c == s.charAt(i + 1)) {
                count++;
                if (count < 3) {
                    sb.append(c);
                }
                i++;
            }
        }

        return sb.toString();
    }
}
