package com.bottomlord.week_019;

import java.util.*;

public class LeetCode_399_1_除法求值 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            String origin = list.get(0);
            String target = list.get(1);

            Map<String, Double> innerMapA = map.get(origin);
            if (innerMapA == null) {
                innerMapA = new HashMap<>();
                innerMapA.put(target, values[i]);
                map.put(origin, innerMapA);
            } else {
                innerMapA.put(target, values[i]);
            }

            Map<String, Double> innerMapB = map.get(target);
            if (innerMapB == null) {
                innerMapB = new HashMap<>();
                innerMapB.put(origin, 1 / values[i]);
                map.put(target, innerMapB);
            } else {
                innerMapB.put(origin, 1 / values[i]);
            }
        }

        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String origin = query.get(0);
            String target = query.get(1);

            if (!map.containsKey(origin) || !map.containsKey(target)) {
                ans[i] = -1.0;
                continue;
            }

            ans[i] = dfs(origin, target, map, new HashSet<>());
        }
        return ans;
    }

    private double dfs(String origin, String target, Map<String, Map<String, Double>> map, Set<String> set) {
        if (Objects.equals(origin, target)) {
            return 1.0;
        }

        set.add(origin);

        Map<String, Double> innerMap = map.get(origin);
        for (Map.Entry<String, Double> entry : innerMap.entrySet()) {
            if (set.contains(entry.getKey())) {
                continue;
            }

            double value = dfs(entry.getKey(), target, map, set);
            if (value != -1.0) {
                return value * entry.getValue();
            }
        }

        return -1.0;
    }
}
