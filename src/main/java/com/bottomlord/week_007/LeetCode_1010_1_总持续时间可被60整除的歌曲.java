package com.bottomlord.week_007;

public class LeetCode_1010_1_总持续时间可被60整除的歌曲 {
    public int numPairsDivisibleBy60(int[] time) {
        int count = 0;
        for (int i = 0; i < time.length; i++) {
            for (int j = i + 1; j < time.length; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}
