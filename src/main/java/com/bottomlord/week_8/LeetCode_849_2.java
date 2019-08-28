package com.bottomlord.week_8;

public class LeetCode_849_2 {
    public int maxDistToClosest(int[] seats) {
        int count = 0, max = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                max = i;
                break;
            }
        }

        for (int i = max + 1; i < seats.length; i++) {
            if (seats[i] == 1) {
                max = Math.max(max, count / 2 + count % 2);
                count = 0;
                continue;
            }

            count++;
        }

        return Math.max(count, max);
    }
}