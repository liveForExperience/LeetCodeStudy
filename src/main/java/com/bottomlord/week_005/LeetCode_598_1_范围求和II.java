package com.bottomlord.week_005;

public class LeetCode_598_1_范围求和II {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return 0;
        }

        int x = ops[0][0], y = ops[0][1];
        for (int i = 1; i < ops.length; i++) {
            int[] op = ops[i];
            x = Math.min(x, op[0]);
            y = Math.min(y, op[1]);
        }

        return x * y;
    }
}
