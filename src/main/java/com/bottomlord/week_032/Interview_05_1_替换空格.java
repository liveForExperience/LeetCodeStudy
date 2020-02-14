package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/14 11:27
 */
public class Interview_05_1_替换空格 {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
