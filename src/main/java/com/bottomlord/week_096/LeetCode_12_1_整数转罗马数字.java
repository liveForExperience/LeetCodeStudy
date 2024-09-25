package com.bottomlord.week_096;

/**
 * @author ChenYue
 * @date 2021/5/14 8:07
 */
public class LeetCode_12_1_整数转罗马数字 {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        if (num / 1000 != 0) {
            int time = num / 1000;
            for (int i = 0; i < time; i++) {
                sb.append("M");
            }
            num %= 1000;
        }

        if (num / 500 != 0) {
            if (num / 900 == 1) {
                sb.append("C").append("M");
                num %= 900;
            } else {
                int time = num / 500;
                for (int i = 0; i < time; i++) {
                    sb.append("D");
                }
                num %= 500;
            }
        }

        if (num / 100 != 0) {
            if (num / 400 == 1) {
                sb.append("C").append("D");
                num %= 400;
            } else {
                int time = num / 100;
                for (int i = 0; i < time; i++) {
                    sb.append("C");
                }
                num %= 100;
            }
        }

        if (num / 50 != 0) {
            if (num / 90 == 1) {
                sb.append("X").append("C");
                num %= 90;
            } else {
                int time = num / 50;
                for (int i = 0; i < time; i++) {
                    sb.append("L");
                }
                num %= 50;
            }
        }

        if (num / 10 != 0) {
            if (num / 40 == 1) {
                sb.append("X").append("L");
                num %= 40;
            } else {
                int time = num / 10;
                for (int i = 0; i < time; i++) {
                    sb.append("X");
                }
                num %= 10;
            }
        }

        if (num == 9) {
            sb.append("I").append("X");
        } else if (num == 4){
            sb.append("I").append("V");
        } else {
            int time = num / 5;
            for (int i = 0; i < time; i++) {
                sb.append("V");
            }
            num %= 5;

            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        }

        return sb.toString();
    }
}
