package com.bottomlord.week_023;

public class LeetCode_134_2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length, total = 0, cur = 0, start = 0;
        for (int i = 0; i < len; i++) {
            total += gas[i] - cost[i];
            cur += gas[i] - cost[i];

            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }

        return total >= 0 ? start : -1;
    }
}