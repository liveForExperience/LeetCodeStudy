package com.bottomlord.contest_20190929;

import java.util.Stack;

public class Contest_3_删除字符串中的所有相邻重复项II {
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1) {
                sb.append(s, i - count, i + 1);
                continue;
            }

            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                if (count == k) {
                    count = 0;
                }
            } else {
                sb.append(s, i - count, i + 1);
                count = 0;
            }
        }

        if (sb.length() == s.length()) {
            return s;
        } else {
            return removeDuplicates(sb.toString(), k);
        }
    }

    public String removeDuplicates1(String s, int k) {
        Stack<int[]> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(new int[]{c, 1});
            } else {
                int[] arr = stack.peek();
                if (arr[0] == c ) {
                    if (arr[1] == k - 1) {
                        stack.pop();
                    } else {
                        stack.peek()[1]++;
                    }
                } else {
                    stack.push(new int[]{c, 1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            char c = (char)arr[0];
            int len = arr[1];
            for (int i = 0; i < len; i++) {
                sb.insert(0, c);
            }
        }
        return sb.toString();
    }
}
