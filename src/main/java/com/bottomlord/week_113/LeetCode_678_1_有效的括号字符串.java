package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-12 17:29:43
 */
public class LeetCode_678_1_有效的括号字符串 {
    public boolean checkValidString(String s) {
        return dfs(0, 0, s);
    }

    private boolean dfs(int index, int count, String s) {
        if (index == s.length()) {
            return count == 0;
        }

        if (count < 0) {
            return false;
        }

        char c = s.charAt(index);
        boolean flag = false;
        if (c == '(') {
            flag = dfs(index + 1, count + 1, s);
        } else if (c == ')') {
            flag = dfs(index + 1, count - 1, s);
        } else {
            flag = dfs(index + 1, count + 1, s) ||
                    dfs(index + 1, count - 1, s) ||
                    dfs(index + 1, count, s);
        }

        return flag;
    }
}
