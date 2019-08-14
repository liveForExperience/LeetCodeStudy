package com.bottomlord.week_6;

public class LeetCode_415_1_字符串相加 {
    public String addStrings(String num1, String num2) {
        StringBuilder ansSb = new StringBuilder();

        char[] cs1 = reserve(num1.toCharArray());
        char[] cs2 = reserve(num2.toCharArray());

        int len = Math.min(cs1.length, cs2.length), carry = 0;

        for (int i = 0; i < len; i++) {
            int val = Integer.parseInt(Character.toString(cs1[i])) + Integer.parseInt(Character.toString(cs2[i])) + carry;
            carry = val / 10;
            ansSb.insert(0, Integer.toString(val % 10));
        }

        if (cs1.length == cs2.length) {
            return carry == 0 ? ansSb.toString() : ansSb.insert(0, carry).toString();
        }

        char[] arr = cs1.length > cs2.length ? cs1 : cs2;
        for (int i = len; i < arr.length; i++) {
            int val = Integer.parseInt(Character.toString(arr[i])) + carry;
            carry = val / 10;
            ansSb.insert(0, Integer.toString(val % 10));
        }

        return carry == 0 ? ansSb.toString() : ansSb.insert(0, carry).toString();
    }

    private char[] reserve(char [] cs) {
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            cs[head] ^= cs[tail];
            cs[tail] ^= cs[head];
            cs[head] ^= cs[tail];
            head++;
            tail--;
        }
        return cs;
    }
}
