package com.bottomlord.week_113;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2021-09-12 18:11:01
 */
public class LeetCode_678_3 {
    public boolean checkValidString(String s) {
        Stack<Integer> lefts = new Stack<>(), stars = new Stack<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == ')') {
                if (!lefts.isEmpty()) {
                    lefts.pop();
                } else if (!stars.isEmpty()) {
                    stars.pop();
                } else {
                    return false;
                }
            } else if (c == '(') {
                lefts.push(i);
            } else if (c == '*') {
                stars.push(i);
            }
        }

        if (lefts.isEmpty()) {
            return true;
        }

        if (lefts.size() > stars.size()) {
            return false;
        }

        while (!lefts.isEmpty()) {
            int li = lefts.pop(), si = stars.pop();
            if (li > si) {
                return false;
            }
        }

        return true;
    }
}
