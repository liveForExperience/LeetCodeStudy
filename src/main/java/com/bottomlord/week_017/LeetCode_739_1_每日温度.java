package com.bottomlord.week_017;

public class LeetCode_739_1_每日温度 {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        ans[T.length - 1] = 0;

        for (int i = 0; i < T.length - 1; i++) {
            if (T[i] == 100) {
                ans[i] = 0;
                continue;
            }

            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }

        return ans;
    }
}
