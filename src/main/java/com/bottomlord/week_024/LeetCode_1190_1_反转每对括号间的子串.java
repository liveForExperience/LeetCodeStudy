package com.bottomlord.week_024;

import java.util.Stack;

public class LeetCode_1190_1_反转每对括号间的子串 {
    public String reverseParentheses(String s) {
        int len = s.length();
        int[] arr = new int[len];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int l = stack.pop();
                arr[i] = l;
                arr[l] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        reserve(0, len - 1, s, arr, sb, false);
        return sb.toString();
    }

    private void reserve(int start, int end, String s, int[] arr, StringBuilder sb, boolean reserve) {
        if (reserve) {
            for (int i = end; i >= start; i--) {
                if (s.charAt(i) == ')') {
                    reserve(arr[i] + 1, i - 1, s, arr, sb, false);
                    i = arr[i];
                } else {
                    sb.append(s.charAt(i));
                }
            }
        } else {
            for (int i = start; i <= end; i++) {
                if (s.charAt(i) == '(') {
                    reserve(i + 1, arr[i] - 1, s, arr, sb, true);
                    i = arr[i];
                } else {
                    sb.append(s.charAt(i));
                }
            }
        }
    }
}
