package com.bottomlord.week_009;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_874_1_模拟行走机器人 {
    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] obstacle: obstacles) {
            if (map.containsKey(obstacle[0])) {
                map.get(obstacle[0]).add(obstacle[1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(obstacle[1]);
                map.put(obstacle[0], list);
            }
        }

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0, max = 0;
        int[] position = new int[2];

        for (int command : commands) {
            if (command == -2) {
                direction = (direction + 3) % 4;
            } else if (command == -1) {
                direction = (direction + 1) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    int x = position[0] + directions[direction][0];
                    int y = position[1] + directions[direction][1];
                    if (map.containsKey(x) && map.get(x).contains(y)) {
                        break;
                    } else {
                        position[0] = x;
                        position[1] = y;
                        max = Math.max(max, x * x + y * y);
                    }
                }
            }
        }

        return max;
    }
}
