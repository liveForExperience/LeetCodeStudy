package com.bottomlord.week_020;

import java.util.Arrays;

public class LeetCode_781_2 {
    public int numRabbits(int[] answers) {
        Arrays.sort(answers);
        int ans = 0;
        for (int i = 0 ; i < answers.length; i++) {
            int num = answers[i], start = i;
            ans += num + 1;
            while (i < answers.length && answers[i] == num && i - start < num + 1) {
                i++;
            }
            i--;
        }
        return ans;
    }
}
