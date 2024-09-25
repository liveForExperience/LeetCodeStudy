package com.bottomlord.week_243;

/**
 * @author chen yue
 * @date 2024-03-10 16:00:13
 */
public class LeetCode_299_1_猜数字游戏 {
    public String getHint(String secret, String guess) {
        int bull = 0, cow = 0, n = secret.length();
        int[] ss = new int[10], gs = new int[10];
        for (int i = 0; i < n; i++) {
            char s = secret.charAt(i), g = guess.charAt(i);
            if (s == g) {
                bull++;
            } else {
                ss[s - '0']++;
                gs[g - '0']++;
            }
        }

        for (int i = 0; i < 10; i++) {
            cow += Math.min(ss[i], gs[i]);
        }

        return bull + "A" + cow + "B";
    }
}