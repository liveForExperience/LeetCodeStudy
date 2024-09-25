package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-25 15:39:42
 */
public class LeetCode_2432_1_处理用时最长的那个任务的员工 {
    public int hardestWorker(int n, int[][] logs) {
        int ans = -1, max = Integer.MIN_VALUE, lastLeave = 0;
        for (int[] log : logs) {
            int id = log[0], leave = log[1], diff = leave - lastLeave;
            if (diff > max) {
                max = diff;
                ans = id;
            } else if (diff == max) {
                ans = ans == -1 ? id : Math.min(ans, id);
            }
            lastLeave = leave;
        }

        return ans;
    }
}
