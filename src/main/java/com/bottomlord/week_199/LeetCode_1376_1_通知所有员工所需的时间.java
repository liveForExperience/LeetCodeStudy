package com.bottomlord.week_199;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-05-01 10:08:47
 */
public class LeetCode_1376_1_通知所有员工所需的时间 {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTimes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            map.computeIfAbsent(manager[i], x -> new ArrayList<>()).add(i);
        }

        return dfs(headID, map, informTimes);
    }

    private int dfs(int manager, Map<Integer, List<Integer>> map, int[] informTimes) {
        int curTime = informTimes[manager], cost = 0;
        for (Integer emp : map.getOrDefault(manager, new ArrayList<>())) {
            cost = Math.max(cost, dfs(emp, map, informTimes));
        }
        return curTime + cost;
    }
}
