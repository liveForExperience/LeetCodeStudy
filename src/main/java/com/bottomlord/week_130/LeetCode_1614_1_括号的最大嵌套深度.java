package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-07 08:52:34
 */
public class LeetCode_1614_1_括号的最大嵌套深度 {
    public int maxDepth(String s) {
        int count = 0, ans = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == '(') {
                count++;
                ans = Math.max(ans, count);
            }

            if (c == ')') {
                count--;
            }
        }

        return ans;
    }
}
