package com.bottomlord.week_6;

public class LeetCode_492_1_构造矩形_fail {
    public int[] constructRectangle(int area) {
        double squareRoot = Math.sqrt(area);
        int num = (int)squareRoot;
        if ((double)num == squareRoot) {
            return new int[]{num, num};
        }

        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];
        for (int i = 1; i < num; i++) {
            for (int j = area; j > num; j--) {
                if (i * j < area) {
                    break;
                }

                if (i * j == area && j - i < min) {
                    min = j - i;
                    ans[0] = j;
                    ans[1] = i;
                }
            }
        }
        return ans;
    }
}
