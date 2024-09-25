package com.bottomlord.week_251;

/**
 * @author chen yue
 * @date 2024-04-30 16:29:26
 */
public class LeetCode_2798_1_满足目标工作时长的员工数目 {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int cnt = 0;
        for (int hour : hours) {
            cnt += hour >= target ? 1 : 0;
        }
        return cnt;
    }
}
