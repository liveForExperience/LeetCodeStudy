package com.bottomlord.week_109;

/**
 * @author ChenYue
 * @date 2021/8/13 8:56
 */
public class LeetCode_1446_1_连续字符 {
    public int maxPower(String s) {
        char x = s.charAt(0);
        int acc = 1, max = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == x) {
                acc++;
            } else {
                max = Math.max(max, acc);
                acc = 1;
                x = s.charAt(i);
            }
        }
        return Math.max(acc, max);
    }
}
