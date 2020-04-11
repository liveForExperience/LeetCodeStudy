package com.bottomlord.week_040;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/4/11 21:32
 */
public class Interview_1615_1_珠玑妙算 {
    public int[] masterMind(String solution, String guess) {
        char[] css = solution.toCharArray(), csg = guess.toCharArray();

        int same = 0;
        for (int i = 0; i < solution.length(); i++) {
            if (css[i] == csg[i]) {
                same++;
            }
        }

        int all = 0;
        int[] bucket1 = new int[26], bucket2 = new int[26];
        for (int i = 0; i < solution.length(); i++) {
            bucket1[css[i] - 'A']++;
            bucket2[csg[i] - 'A']++;
        }

        for (int i = 0; i < bucket1.length; i++) {
            all += Math.min(bucket1[i], bucket2[i]);
        }

        return new int[]{same, all - same};
    }
}