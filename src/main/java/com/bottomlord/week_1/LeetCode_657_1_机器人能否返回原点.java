package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/10 12:37
 */
public class LeetCode_657_1_机器人能否返回原点 {
    public boolean judgeCircle(String moves) {
        int[] record = new int[2];
        for(char c: moves.toCharArray()) {
            if (c == 'R') {
                record[0] -= 1;
            }

            if (c == 'L') {
                record[0] += 1;
            }

            if (c == 'U') {
                record[1] += 1;
            }

            if (c == 'R') {
                record[1] -= 1;
            }
        }

        for (int i: record) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}
