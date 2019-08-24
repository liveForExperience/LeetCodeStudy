package com.bottomlord.week_7;

public class LeetCode_1010_3 {
    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        int[] bucket = new int[60];
        for (int num : time) {
            ans += bucket[(60 - num % 60) % 60];
            bucket[num % 60]++;
        }
        return ans;
    }
}