package com.bottomlord.week_7;

public class LeetCode_1010_2 {
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length == 0) {
            return 0;
        }

        int[] bucket = new int[60];
        for (int i = 0; i < time.length; i++) {
            bucket[time[i] % 60]++;
        }

        int count = 0;
        for (int i = 1; i < 30; i++) {
            if (bucket[i] == 0  || bucket[60 - i] == 0) {
                continue;
            }
            count += bucket[i] * bucket[60 - i];
        }

        count += bucket[0] * (bucket[0] - 1) / 2;
        count += bucket[30] * (bucket[30] - 1) / 2;

        return count;
    }
}