package com.bottomlord.week_136;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-02-18 09:40:09
 */
public class LeetCode_582_1_杀掉进程 {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Integer> mapping = new HashMap<>();
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            mapping.put(i, pid.get(i));
        }

        for (int i = 0; i < ppid.size(); i++) {
            edges.computeIfAbsent(ppid.get(i), x -> new ArrayList<>()).add(mapping.get(i));
        }

        List<Integer> ans = new ArrayList<>();
        dfs(kill, ans, edges);
        return ans;
    }

    private void dfs(Integer kill, List<Integer> list, Map<Integer, List<Integer>> edges) {
        if (kill == null) {
            return;
        }

        list.add(kill);
        List<Integer> nexts = edges.get(kill);
        if (nexts != null) {
            for (Integer next : nexts) {
                dfs(next, list, edges);
            }
        }
    }
}
