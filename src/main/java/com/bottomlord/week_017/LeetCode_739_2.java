package com.bottomlord.week_017;

public class LeetCode_739_2 {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        ans[T.length - 1] = 0;

        for (int i = T.length - 2; i >= 0; i--) {
            ans[i] = recurse(i, T, ans, 1);
        }

        return ans;
    }

    private int recurse(int index, int[] T, int[] ans, int path) {
        if (index + path >= T.length) {
            return 0;
        }

        if (T[index] < T[index + path]) {
            return path;
        }

        if (ans[index + path] == 0) {
            return 0;
        }

        return recurse(index, T, ans, path + ans[index + path]);
    }

    public static void main(String[] args) {
        LeetCode_739_2 t = new LeetCode_739_2();
        t.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
    }
}