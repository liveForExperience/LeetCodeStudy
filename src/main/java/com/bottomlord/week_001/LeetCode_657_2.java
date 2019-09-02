package com.bottomlord.week_001;

/**
 * @author LiveForExperience
 * @date 2019/7/10 12:54
 */
public class LeetCode_657_2 {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char c: moves.toCharArray()) {
            if (c == 'L') {
                x++;
            }

            if (c == 'R') {
                x--;
            }

            if (c == 'U') {
                y++;
            }

            if (c == 'D') {
                y--;
            }
        }

        return x == 0 && y == 0;
    }
}
