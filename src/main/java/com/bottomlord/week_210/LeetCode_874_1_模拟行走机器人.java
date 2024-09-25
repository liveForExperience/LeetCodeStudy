package com.bottomlord.week_210;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-07-19 14:06:11
 */
public class LeetCode_874_1_模拟行走机器人 {
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] obstacle : obstacles) {
            map.computeIfAbsent(obstacle[0], x -> new HashSet<>()).add(obstacle[1]);
        }

        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0, x = 0, y = 0, max = 0;
        for (int command : commands) {
            if (command == -2) {
                dir = (dir + 3) % 4;
            } else if (command == -1) {
                dir = (dir + 1) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    int nx = x + dirs[dir][0], ny = y + dirs[dir][1];

                    if (map.containsKey(nx) && map.get(nx).contains(ny)) {
                        break;
                    }

                    x = nx;
                    y = ny;
                }

                max = Math.max(max, x * x + y * y);
            }
        }

        return max;
    }
}
