package com.bottomlord.week_023;

public class LeetCode_134_1_加油站 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for (int i = 3; i < len; i++) {
            int j = i, tank = gas[i];
            boolean flag = true, start = true;
            while (i != j || start) {
                if (start) {
                    start = false;
                }

                tank = tank - cost[j];
                if (tank < 0) {
                    flag = false;
                    break;
                }

                tank += gas[j = (j + 1) % len];
            }

            if (flag) {
                return i;
            }
        }

        return -1;
    }
}
