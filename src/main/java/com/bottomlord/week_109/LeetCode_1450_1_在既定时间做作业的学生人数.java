package com.bottomlord.week_109;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-15 14:26:53
 */
public class LeetCode_1450_1_在既定时间做作业的学生人数 {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime) {
                list.add(i);
            }
        }

        int ans = 0;
        for (int index : list) {
            if (endTime[index] >= queryTime) {
                ans++;
            }
        }

        return ans;
    }
}
