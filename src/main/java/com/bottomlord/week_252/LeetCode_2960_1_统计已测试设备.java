package com.bottomlord.week_252;

/**
 * @author chen yue
 * @date 2024-05-10 17:33:41
 */
public class LeetCode_2960_1_统计已测试设备 {
    public int countTestedDevices(int[] batteryPercentages) {
        int cnt = 0;
        for (int p : batteryPercentages) {
            if (p - cnt > 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
