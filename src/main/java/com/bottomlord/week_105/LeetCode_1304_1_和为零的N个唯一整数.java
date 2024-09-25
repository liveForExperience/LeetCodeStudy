package com.bottomlord.week_105;

public class LeetCode_1304_1_和为零的N个唯一整数 {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        boolean even = n % 2 == 0;
        int index = even ? 0 : 1, num = 1;
        while (index < n) {
            ans[index++] = num;
            ans[index++] = -num;
            num++;
        }

        return ans;
    }
}
