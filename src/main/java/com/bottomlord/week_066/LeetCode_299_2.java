package com.bottomlord.week_066;

/**
 * @author ChenYue
 * @date 2020/10/14 8:50
 */
public class LeetCode_299_2 {
    public String getHint(String secret, String guess) {
        int[] s = new int[10],
              g = new int[10];

        int bull = 0, cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            s[secret.charAt(i) - '0']++;
            g[guess.charAt(i) - '0']++;

            bull += secret.charAt(i) == guess.charAt(i) ? 1 : 0;
        }

        for (int i = 0; i < s.length; i++) {
            cow += Math.min(s[i], g[i]);
        }

        cow -= bull;

        return bull + "A" + cow + "B";
    }
}
