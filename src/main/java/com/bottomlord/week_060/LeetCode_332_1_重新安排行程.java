package com.bottomlord.week_060;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/27 8:16
 */
public class LeetCode_332_1_重新安排行程 {
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort(Comparator.comparing(x -> x.get(1)));
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String key = ticket.get(0);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(ticket.get(1));
            map.put(key, list);
        }

        List<String> ans = new ArrayList<>();
        boolean find = backTrack("JFK", map, ans);
        return find ? ans : Collections.emptyList();
    }

    private boolean backTrack(String key, Map<String, List<String>> map, List<String> ans) {
        ans.add(key);
        if (empty(map)) {
            return true;
        }

        List<String> value = map.get(key);
        if (value == null) {
            return false;
        }

        List<String> list = new ArrayList<>(value);
        for (int i = 0; i < list.size(); i++) {
            String end = value.get(i);
            value.remove(end);
            boolean find = backTrack(end, map, ans);
            if (find) {
                return true;
            }
            ans.remove(ans.size() - 1);
            value.add(i, end);
        }

        return false;
    }

    private boolean empty(Map<String, List<String>> map) {
        for (List<String> list : map.values()) {
            if (!list.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}