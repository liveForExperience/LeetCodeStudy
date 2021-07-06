package com.bottomlord.week_104;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/7/6 8:43
 */
public class LeetCode_1418_2 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        int maxTable = 0;
        Set<String> foodSet = new HashSet<>(), tableSet = new HashSet<>();
        for (List<String> order : orders) {
            tableSet.add(order.get(1));
            foodSet.add(order.get(2));
            maxTable = Math.max(maxTable, Integer.parseInt(order.get(1)));
        }

        List<String> foods = new ArrayList<>(foodSet), tables = new ArrayList<>(tableSet);
        foods.sort(null);
        foods.add(0, "Table");
        tables.sort(null);

        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 1; i < foods.size(); i++) {
            idxMap.put(foods.get(i), i);
        }

        List<List<String>> ans = new ArrayList<>();
        ans.add(foods);
        List<String>[] list = new ArrayList[maxTable + 1];

        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));

            if (list[table] == null) {
                list[table] = new ArrayList<>();
                list[table].add("" + table);
                for (int i = 1; i < foods.size(); i++) {
                    list[table].add("0");
                }
            }

            int idx = idxMap.get(order.get(2));
            list[table].set(idx, "" + (Integer.parseInt(list[table].get(idx)) + 1));
        }

        for (List<String> strings : list) {
            if (strings != null) {
                ans.add(strings);
            }
        }

        return ans;
    }
}
