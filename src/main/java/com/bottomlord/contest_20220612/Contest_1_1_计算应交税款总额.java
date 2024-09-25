package com.bottomlord.contest_20220612;

/**
 * @author chen yue
 * @date 2022-06-12 10:30:26
 */
public class Contest_1_1_计算应交税款总额 {
    public double calculateTax(int[][] brackets, int income) {
        double sum = 0D;
        boolean flag = false;
        for (int i = 0; i < brackets.length; i++) {
            int[] cur = brackets[i];
            int x1 = cur[0], y1 = cur[1];

            if (income <= x1) {
                flag = true;
            }

            if (i == 0) {
                sum += 1D * Math.min(x1, income) * y1 / 100;
            } else {
                int[] pre = brackets[i - 1];
                sum += 1D * (Math.min(x1, income) - pre[0]) * y1 / 100;
            }

            if (flag) {
                break;
            }
        }

        return sum;
    }
}
