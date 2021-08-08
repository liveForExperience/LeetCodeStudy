package com.bottomlord.week_108;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-08-08 15:51:11
 */
public class LeetCode_1436_1_旅行终点站 {
    public String destCity(List<List<String>> paths) {
        Map<String, List<String>> map = new HashMap<>();
        Set<String> cities = new HashSet<>();
        for (List<String> path : paths) {
            cities.add(path.get(1));
            map.computeIfAbsent(path.get(0), x -> new ArrayList<>()).add(path.get(1));
        }

        for (String city : cities) {
            if (!map.containsKey(city)) {
                return city;
            }
        }

        return "";
    }
}
