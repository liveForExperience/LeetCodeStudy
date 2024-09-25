package com.bottomlord.week_104;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/7/6 8:16
 */
public class LeetCode_1418_1_点菜展示表 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        TreeSet<String> set = new TreeSet<>();
        TreeMap<String, TreeMap<String, Integer>> map = new TreeMap<>(Comparator.comparingInt(Integer::parseInt));
        for (List<String> order : orders) {
            set.add(order.get(2));
            map.put(order.get(1), new TreeMap<>());
        }

        for (Map.Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
            TreeMap<String, Integer> treeMap = entry.getValue();
            for (String str : set) {
                treeMap.put(str, 0);
            }
        }

        for (List<String> order : orders) {
            TreeMap<String, Integer> treeMap = map.get(order.get(1));
            treeMap.put(order.get(2), treeMap.getOrDefault(order.get(2), 0) + 1);
        }

        List<List<String>> ans = new ArrayList<>();
        List<String> head = new ArrayList<>();
        head.add("Table");
        head.addAll(set);
        ans.add(head);

        for (Map.Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(entry.getKey());
            list.addAll(entry.getValue().values().stream().map(String::valueOf).collect(Collectors.toList()));
            ans.add(list);
        }

        return ans;
    }
}
