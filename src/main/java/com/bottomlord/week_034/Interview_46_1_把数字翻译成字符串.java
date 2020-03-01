package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/3/1 20:28
 */
public class Interview_46_1_把数字翻译成字符串 {
    public int translateNum(int num) {
        return recurse(String.valueOf(num), 0);
    }

    private int recurse(String num, int index) {
        if (index >= num.length()) {
            return 0;
        }

        if (index == num.length() - 1) {
            return 1;
        }

        int one = recurse(num, index + 1), two = 0;
        if (index + 2 < num.length() && num.charAt(index + 1) != '0' && Integer.parseInt(num.substring(index + 1, index + 3)) <= 25) {
            two = recurse(num, index + 2);
        }

        return one + two;
    }
}
