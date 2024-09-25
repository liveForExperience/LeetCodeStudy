package com.bottomlord.week_028;

/**
 * @author ThinkPad
 * @date 2020/1/17 17:23
 */
public class LeetCode_394_2 {
    public String decodeString(String s) {
        return dfs(s, 0)[0];
    }

    private String[] dfs(String s, int index) {
        int num = 0;
        StringBuilder str = new StringBuilder();

        while (index < s.length()){
            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (Character.isLetter(c)) {
                str.append(c);
            } else if ('[' == c) {
                String[] returns = dfs(s, index + 1);
                index = Integer.parseInt(returns[1]);
                while (num > 0) {
                    str.append(returns[0]);
                    num--;
                }
            } else {
                return new String[]{str.toString(), String.valueOf(index)};
            }
            index++;
        }

        return new String[]{str.toString()};
    }
}