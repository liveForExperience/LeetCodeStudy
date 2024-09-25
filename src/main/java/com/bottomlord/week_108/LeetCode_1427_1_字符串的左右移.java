package com.bottomlord.week_108;

/**
 * @author ChenYue
 * @date 2021/8/3 8:48
 */
public class LeetCode_1427_1_字符串的左右移 {
    public String stringShift(String s, int[][] shift) {
        int l = 0, r = 0;
        for (int[] sh : shift) {
            if (sh[0] == 0) {
                l += sh[1];
            } else {
                r += sh[1];
            }
        }

        int direction = 0, move = Math.abs(l - r) % s.length();
        if (l == r) {
            return s;
        } else if (r > l) {
            direction = 1;
        }

        StringBuilder sb = new StringBuilder();
        if (direction == 0) {
            for (int i = move; i < s.length(); i++) {
                sb.append(s.charAt(i));
            }

            for (int i = 0; i < move; i++) {
                sb.append(s.charAt(i));
            }
        } else {
            for (int i = s.length() - move; i < s.length() - 1 ; i++) {
                sb.append(s.charAt(i));
            }

            for (int i = 0; i < s.length() - move; i++) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
