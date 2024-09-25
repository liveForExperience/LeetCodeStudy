package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-09-29 08:31:06
 */
public class LeetCode_517_1_超级洗衣机 {
    public int findMinMoves(int[] machines) {
        int sum = 0, n = machines.length;
        for (int machine : machines) {
            sum += machine;
        }

        if (sum % n != 0) {
            return -1;
        }

        int average = sum / n;
        for (int i = 0; i < n; i++) {
            machines[i] = machines[i] - average;
        }

        int ans = 0, leftSum = 0;
        for (int machine : machines) {
            leftSum += machine;
            int cur = Math.max(Math.abs(leftSum), machine);
            ans = Math.max(cur, ans);
        }

        return ans;
    }
}
