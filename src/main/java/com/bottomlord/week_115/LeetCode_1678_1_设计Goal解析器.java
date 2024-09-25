package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-19 21:00:28
 */
public class LeetCode_1678_1_设计Goal解析器 {
    public String interpret(String command) {
        char[] cs = command.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            if ('G' == c) {
                sb.append(c);
                i++;
            } else {
                char nextC = command.charAt(++i);
                if (nextC == ')') {
                    sb.append("o");
                    i++;
                } else {
                    sb.append("al");
                    i += 3;
                }
            }
        }

        return sb.toString();
    }
}
