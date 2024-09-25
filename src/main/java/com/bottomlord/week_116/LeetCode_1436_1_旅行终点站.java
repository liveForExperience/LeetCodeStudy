package com.bottomlord.week_116;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-10-01 11:30:45
 */
public class LeetCode_1436_1_旅行终点站 {
    public String destCity(List<List<String>> paths) {
        Set<String> departs = new HashSet<>(),
                    dests = new HashSet<>();

        for (List<String> path : paths) {
            departs.add(path.get(0));
            dests.add(path.get(1));
        }

        for (String dest : dests) {
            if (!departs.contains(dest)) {
                return dest;
            }
        }

        return null;
    }
}
