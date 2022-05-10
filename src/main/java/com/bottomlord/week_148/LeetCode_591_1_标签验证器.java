package com.bottomlord.week_148;

import java.util.Objects;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-05-09 22:15:07
 */
public class LeetCode_591_1_标签验证器 {
    public boolean isValid(String code) {
        int n = code.length(), i = 0;
        Stack<String> stack = new Stack<>();

        while (i < n) {
            char c = code.charAt(i);
            if (c == '<') {
                if (i == n - 1) {
                    return false;
                }

                if (code.charAt(i + 1) == '/') {
                    int j = code.indexOf('>', i);
                    if (j < 0) {
                        return false;
                    }

                    String tagName = code.substring(i + 2, j);
                    if (stack.isEmpty() || !stack.peek().equals(tagName)) {
                        return false;
                    }

                    stack.pop();

                    i = j + 1;
                    if (stack.isEmpty() && i != n) {
                        return false;
                    }
                } else if (code.charAt(i + 1) == '!') {
                    if (stack.isEmpty()) {
                        return false;
                    }

                    if (i + 9 > n) {
                        return false;
                    }

                    String cdata = code.substring(i + 2, i + 9);
                    if (!Objects.equals(cdata, "[CDATA[")) {
                        return false;
                    }

                    int j = code.indexOf("]]>", i);
                    if (j < 0) {
                        return false;
                    }

                    i = j + 3;
                } else {
                    int j = code.indexOf(">", i);
                    if (j < 0) {
                        return false;
                    }

                    String tagName = code.substring(i + 1, j);
                    if (tagName.length() < 1 || tagName.length() > 9) {
                        return false;
                    }

                    for (int k = 0; k < tagName.length(); k++) {
                        if (!Character.isUpperCase(tagName.charAt(k))) {
                            return false;
                        }
                    }

                    stack.push(tagName);
                    i = j + 1;
                }
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                i++;
            }
        }

        return stack.isEmpty();
    }
}
