package com.bottomlord.week_092;

import java.util.Arrays;

public class LeetCode_517_1_超级洗衣机 {
    public int findMinMoves(int[] machines) {
        int len = machines.length, sum = Arrays.stream(machines).sum();
        if (sum % len != 0) {
            return -1;
        }

        int avg = sum / len;
        for (int i = 0; i < len; i++) {
            machines[i] = machines[i] - avg;
        }

        int ans = 0, leftSum = 0;
        for (int machine : machines) {
            leftSum += machine;
            int cur = Math.max(Math.abs(leftSum), machine);
            ans = Math.max(ans, cur);
        }

        return ans;
    }
}

