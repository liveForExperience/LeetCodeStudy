package com.bottomlord.contest_20200419;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/19 10:52
 */
public class Contest_2_1_点菜展示表 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> foodSet =  new HashSet<>();
        Set<Integer> tableSet = new HashSet<>();
        Map<Integer, Map<String, Integer>> map = new HashMap<>();

        for (List<String> order : orders) {
            String food = order.get(2);
            foodSet.add(food);

            Integer table = Integer.parseInt(order.get(1));
            tableSet.add(table);

            Map<String, Integer> innerMap = map.getOrDefault(table, new HashMap<>());
            innerMap.put(food, innerMap.getOrDefault(food, 0) + 1);
            map.put(table, innerMap);
        }

        List<String> foods = new ArrayList<>(foodSet);
        List<Integer> tables = new ArrayList<>(tableSet);

        Collections.sort(foods);
        Collections.sort(tables);

        List<List<String>> ans = new ArrayList<>();
        List<String> first = new ArrayList<>();
        first.add("Table");
        first.addAll(foods);
        ans.add(first);

        for (Integer table : tables) {
            List<String> row = new ArrayList<>();
            row.add("" + table);
            for (String food : foods) {
                if (map.containsKey(table)) {
                    Map<String, Integer> innerMap = map.get(table);
                    Integer num = innerMap.getOrDefault(food, 0);
                    row.add("" + num);
                }
            }
            ans.add(row);
        }

        return ans;
    }
}
