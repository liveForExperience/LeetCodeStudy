package com.bottomlord.week_108;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-08-08 15:59:52
 */
public class LeetCode_1436_2 {
    public String destCity(List<List<String>> paths) {
        Set<String> depart = new HashSet<>(),
                    dest = new HashSet<>();

        for (List<String> path : paths) {
            depart.add(path.get(0));
            dest.add(path.get(1));
        }

        for (String city : dest) {
            if (!depart.contains(city)) {
                return city;
            }
        }

        return null;
    }
}
