package com.bottomlord.week_013;

public class LeetCode_12_1_整数转罗马数字 {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        int t = num / 1000;
        num %= 1000;

        for (int i = 0; i < t; i++) {
            sb.append("M");
        }

        int h = num / 100;
        num %= 100;

        if (h == 9) {
            sb.append("CM");
        } else if (h == 4) {
            sb.append("CD");
        } else if (h >= 5) {
            sb.append("D");
            h -= 5;
            for (int i = 0; i < h; i++) {
                sb.append("C");
            }
        } else {
            for (int i = 0; i < h; i++) {
                sb.append("C");
            }
        }

        int d = num / 10;
        num %= 10;

        if (d == 9) {
            sb.append("XC");
        } else if (d == 4) {
            sb.append("XL");
        } else if (d >= 5) {
            sb.append("L");
            d -= 5;
            for (int i = 0; i < d; i++) {
                sb.append("X");
            }
        } else {
            for (int i = 0; i < d; i++) {
                sb.append("X");
            }
        }

        if (num == 9) {
            sb.append("IX");
        } else if (num == 4) {
            sb.append("IV");
        } else if (num >= 5) {
            sb.append("V");
            num -= 5;
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        } else {
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        }

        return sb.toString();
    }
}
