package com.bottomlord.contest_20200308;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/3/8 10:50
 */
public class Contest_3_通知所有员工所需的时间 {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            List<Integer> list = map.getOrDefault(manager[i], new ArrayList<>());
            list.add(i);
            map.put(manager[i], list);
        }
        return bfs(headID, map, informTime);
    }

    private int bfs(int headId, Map<Integer, List<Integer>> map, int[] inforTime) {
        int time = 0, headTime = inforTime[headId];

        List<Integer> list = map.getOrDefault(headId, new ArrayList<>());
        for (int index : list) {
            time = Math.max(bfs(index, map, inforTime), time);
        }

        return headTime + time;
    }
}
