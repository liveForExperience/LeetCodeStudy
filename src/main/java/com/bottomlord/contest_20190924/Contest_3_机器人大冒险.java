package com.bottomlord.contest_20190924;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Contest_3_机器人大冒险 {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        char[] commands = command.toCharArray();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int ox = obstacle[0];
            int oy = obstacle[1];

            if (map.containsKey(ox)) {
                map.get(ox).add(oy);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(oy);
                map.put(ox, set);
            }
        }

        int lx = 0, ly = 0;
        while (true) {
            for (char c : commands) {
                if (c == 'U') {
                    ly++;
                } else if (c == 'R') {
                    lx++;
                }

                if (lx == x && ly == y) {
                    return true;
                }

                if (lx > x || ly > y) {
                    return false;
                }

                if (map.containsKey(lx)) {
                    if (map.get(lx).contains(ly)) {
                        return false;
                    }
                }
            }
        }
    }
}
