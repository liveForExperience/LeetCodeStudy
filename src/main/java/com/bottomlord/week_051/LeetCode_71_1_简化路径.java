package com.bottomlord.week_051;

import java.util.Objects;
import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/6/26 14:45
 */
public class LeetCode_71_1_简化路径 {
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String str : strs) {
            if (Objects.equals(str, "..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!Objects.equals(str, ".") && !Objects.equals(str, "")) {
                stack.push(str);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/").append(s);
        }
        return sb.toString();
    }
}
