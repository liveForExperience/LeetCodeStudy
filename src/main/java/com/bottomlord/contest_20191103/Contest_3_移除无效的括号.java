package com.bottomlord.contest_20191103;

import java.util.Stack;

public class Contest_3_移除无效的括号 {
    public String minRemoveToMakeValid(String s) {
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            }

            if (c == ')') {
                if (left > right) {
                    right++;
                }
            }
        }

        left = Math.min(left, right);

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (left > 0) {
                    left--;
                    sb.append(c);
                }
            } else if (c == ')') {
                if (right > 0) {
                    right--;
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
