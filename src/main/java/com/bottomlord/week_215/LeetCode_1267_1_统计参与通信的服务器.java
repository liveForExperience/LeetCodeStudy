package com.bottomlord.week_215;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-08-24 17:25:07
 */
public class LeetCode_1267_1_统计参与通信的服务器 {
    public int countServers(int[][] grid) {
        Map<Integer, Integer> rmap = new HashMap<>(), cmap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                rmap.put(i, rmap.getOrDefault(i, 0) + 1);
                cmap.put(i, cmap.getOrDefault(j, 0) + 1);
            }
        }

        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                if (rmap.get(i) > 1 || cmap.get(j) > 1) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        LeetCode_1267_1_统计参与通信的服务器 t = new LeetCode_1267_1_统计参与通信的服务器();
        t.countServers(new int[][]{{1,0},{1,1}});
    }
}
