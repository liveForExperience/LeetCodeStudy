package com.bottomlord.week_008;

public class LeetCode_754_1_到达终点数字 {
    public int reachNumber(int target) {
        int sum = 0, path = 1;
        target = Math.abs(target);

        while (true) {
            sum += path++;
            if (sum == target) {
                return path - 1;
            }

            if (sum > target && (sum - target) % 2 == 0) {
                return path - 1;
            }
        }
    }
}
