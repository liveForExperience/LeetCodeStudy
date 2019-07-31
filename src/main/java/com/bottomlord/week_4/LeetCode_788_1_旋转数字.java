package com.bottomlord.week_4;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/31 13:13
 */
public class LeetCode_788_1_旋转数字 {
    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 0; i <= N; i++) {
            String str = Integer.toString(i);
            if (str.contains("3") || str.contains("4") || str.contains("7")) {
                continue;
            }

            if (!str.contains("2") && !str.contains("5") && !str.contains("6") && !str.contains("9")) {
                continue;
            }

            count++;
        }
        return count;
    }
}
