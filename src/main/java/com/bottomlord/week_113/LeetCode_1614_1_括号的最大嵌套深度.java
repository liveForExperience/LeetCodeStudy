package com.bottomlord.week_113;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2021-09-06 19:15:18
 */
public class LeetCode_1614_1_括号的最大嵌套深度 {
    public int maxDepth(String s) {
        int depth = 0, max = 0;
        char[] cs = s.toCharArray();

        for (char c : cs) {
            if (c == '(') {
                depth++;
                max = Math.max(depth, max);
            } else if (c == ')') {
                depth--;
            }
        }

        return max;
    }
}
