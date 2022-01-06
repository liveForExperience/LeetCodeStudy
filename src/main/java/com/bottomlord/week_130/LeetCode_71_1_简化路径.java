package com.bottomlord.week_130;

import java.util.Objects;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-01-06 22:58:53
 */
public class LeetCode_71_1_简化路径 {
    public String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String s : arr) {
            if (Objects.equals("", s) || Objects.equals(".", s)) {
                continue;
            }

            if (Objects.equals("..", s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }

            stack.push(s);
        }

        if (stack.isEmpty()) {
            return "/";
        }

        return "/" + String.join("/", stack);
    }
}
