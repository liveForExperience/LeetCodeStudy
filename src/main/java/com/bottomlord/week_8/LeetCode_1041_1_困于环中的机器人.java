package com.bottomlord.week_8;

public class LeetCode_1041_1_困于环中的机器人 {
    public boolean isRobotBounded(String instructions) {
        int[] p = new int[2];
        int face = 0;

        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                face = (face + 3) % 4;
            } else if (c == 'R') {
                face = (face + 1) % 4;
            } else if (c == 'G') {
                if (face == 0) {
                    p[1]++;
                }

                if (face == 1) {
                    p[0]++;
                }

                if (face == 2) {
                    p[1]--;
                }

                if (face == 3) {
                    p[0]--;
                }
            }
        }

        if (p[0] == 0 && p[1] == 0) {
            return true;
        }

        return face != 0;
    }
}
