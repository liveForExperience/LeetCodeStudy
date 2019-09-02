package com.bottomlord.week_008;

public class LeetCode_849_1_到最近的人的最大距离 {
    public int maxDistToClosest(int[] seats) {
        int max = 0, left = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                left = i;
                continue;
            }

            if (seats[i] == 0) {
                int leftLen = left == -1 ? -1 : i - left;
                int rightLen = -1;
                for (int j = i + 1; j < seats.length; j++) {
                    if (seats[j] == 1) {
                        rightLen = j - i;
                        break;
                    }
                }
                max = Math.max(max, leftLen == -1 ? rightLen : rightLen == -1 ? leftLen : Math.min(leftLen, rightLen));
            }
        }

        return max;
    }
}
