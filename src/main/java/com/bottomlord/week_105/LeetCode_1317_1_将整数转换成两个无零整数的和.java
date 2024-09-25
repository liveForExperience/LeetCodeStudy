package com.bottomlord.week_105;

public class LeetCode_1317_1_将整数转换成两个无零整数的和 {
    public int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            int a = i, b = n - i;
            String as = Integer.toString(a),
                   bs = Integer.toString(b);

            if (!as.contains("0") && !bs.contains("0")) {
                return new int[]{a, b};
            }
        }

        return new int[0];
    }
}
