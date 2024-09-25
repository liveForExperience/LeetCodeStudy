package com.bottomlord.week_082;

/**
 * @author ChenYue
 * @date 2021/2/2 8:22
 */
public class LeetCode_439_1_三元表达式解析器 {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
            return expression;
        }

        int len = expression.length(), count = 0;
        for (int i = 1; i < len; i++) {
            if (expression.charAt(i) == '?') {
                count++;
            }

            if (expression.charAt(i) == ':') {
                count--;
            }

            if (count == 0) {
                return expression.charAt(0) == 'T' ? parseTernary(expression.substring(2, i)) : parseTernary(expression.substring(i + 1, len));
            }
        }

        return expression;
    }
}
