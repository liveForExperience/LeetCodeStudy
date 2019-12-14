package com.bottomlord.week_023;

public class LeetCode_134_3 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length, total = 0, lowest = Integer.MAX_VALUE, index = 0;
        for (int i = 0; i < len; i++) {
            total += gas[i] - cost[i];

            if (total < lowest) {
                lowest = total;
                index = i;
            }
        }

        return total < 0 ? -1 : (index + 1) % len;
    }
}