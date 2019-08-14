package com.bottomlord.week_6;

public class LeetCode_415_2 {
    public String addStrings(String num1, String num2) {
        char[] cs1;
        char[] cs2;
        if (num1.length() > num2.length()) {
            cs1 = num1.toCharArray();
            cs2 = num2.toCharArray();
        } else {
            cs1 = num2.toCharArray();
            cs2 = num1.toCharArray();
        }

        int carry = 0;
        int i = 1;
        for (; i <= cs2.length; i++) {
            cs1[cs1.length - i] += cs2[cs2.length - i] - '0' + carry;
            if (cs1[cs1.length - i] > '9') {
                cs1[cs1.length - i] -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if (carry > 0) {
            for (; i <= cs1.length; i++) {
                cs1[cs1.length - i] += carry;
                if (cs1[cs1.length - i] > '9') {
                    cs1[cs1.length - i] -= 10;
                    carry = 1;
                } else {
                    carry = 0;
                    break;
                }
            }
        }

        return carry > 0 ? "1" + String.valueOf(cs1) : String.valueOf(cs1);
    }
}
