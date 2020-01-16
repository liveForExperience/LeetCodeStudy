package com.bottomlord.week_028;

import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/1/16 17:33
 */
public class LeetCode_394_1_字符串解码 {
    public String decodeString(String s) {
        Stack<String> strs = new Stack<>();
        Stack<Integer> nums = new Stack<>();
        StringBuilder str = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                strs.push(str.toString());
                nums.push(num);
                num = 0;
                str = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int time = nums.pop();
                for (int i = 0; i < time; i++) {
                    tmp.append(str);
                }
                str = new StringBuilder((strs.isEmpty() ? "" : strs.pop()) + tmp);
            } else if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            } else {
                str.append(c);
            }
        }
        return str.toString();
    }
}
