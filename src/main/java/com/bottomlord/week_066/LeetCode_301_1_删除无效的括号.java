package com.bottomlord.week_066;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/10/15 8:19
 */
public class LeetCode_301_1_删除无效的括号 {
    private Set<String> set = new HashSet<>();
    private int minRemoveCount = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses(String s) {
        backTrack(s, 0, 0, 0, 0, new StringBuilder());
        return new ArrayList<>(set);
    }

    private void backTrack(String s, int index, int l, int r, int removeCount, StringBuilder expression) {
        if (index == s.length()) {
            if (l == r) {
                if (removeCount <= minRemoveCount) {
                    String str = expression.toString();

                    if (removeCount < minRemoveCount) {
                        set.clear();
                        minRemoveCount = removeCount;
                    }

                    set.add(str);
                }
            }
        } else {
            char c = s.charAt(index);
            int len = expression.length();
            if (c != '(' && c != ')') {
                expression.append(c);
                backTrack(s, index + 1, l, r, removeCount, expression);
                expression.deleteCharAt(len);
            } else {
                backTrack(s, index + 1, l, r, removeCount + 1, expression);
                expression.append(c);

                if (c == '(') {
                    backTrack(s, index + 1, l + 1, r, removeCount, expression);
                } else if (r < l) {
                    backTrack(s, index + 1, l, r + 1, removeCount, expression);
                }

                expression.deleteCharAt(len);
            }
        }
    }
}
