package com.bottomlord.week_021;

public class LeetCode_495_1_提莫攻击 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int end = 0, ans = 0;
        for (int time : timeSeries) {
            if (time >= end) {
                ans += duration;
            } else {
                ans += time + duration - 1 - end;
            }
            end = time + duration - 1;
        }
        return ans;
    }
}
