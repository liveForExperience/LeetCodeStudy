package com.bottomlord.week_137;

/**
 * @author chen yue
 * @date 2022-02-21 08:49:44
 */
public class LeetCode_838_1_推多米诺 {
    public String pushDominoes(String dominoes) {
        dominoes = "L" + dominoes + "R";
        int len = dominoes.length();

        int l = 0;
        StringBuilder sb = new StringBuilder();
        for (int r = 1; r < len; r++) {
            if (dominoes.charAt(r) == '.') {
                continue;
            }

            if (l != 0) {
                sb.append(dominoes.charAt(l));
            }

            int dis = r - l - 1;
            if (dominoes.charAt(l) == dominoes.charAt(r)) {
                for (int i = 0; i < dis; i++) {
                    sb.append(dominoes.charAt(l));
                }
            } else if (dominoes.charAt(l) == 'L' && dominoes.charAt(r) == 'R') {
                for (int i = 0; i < dis; i++) {
                    sb.append(".");
                }
            } else {
                for (int i = 0; i < dis / 2; i++) {
                    sb.append("R");
                }

                if (dis % 2 == 1) {
                    sb.append(".");
                }

                for (int i = 0; i < dis / 2; i++) {
                    sb.append('L');
                }
            }

            l = r;
        }

        return sb.toString();
    }
}
