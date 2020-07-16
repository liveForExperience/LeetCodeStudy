package com.bottomlord.week_054;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/7/16 10:06
 */
public class LeetCode_785_1_判断二分图 {
    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] color = new int[len];

        for (int i = 0; i < len; i++) {
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);

            while (!queue.isEmpty()) {
                Integer num = queue.poll();
                if (num == null) {
                    continue;
                }

                color[num] = color[num] == 0 ? 1 : color[num];
                int[] arr = graph[num];
                for (int index : arr) {
                    if (color[index] == 0) {
                        queue.offer(index);
                        color[index] = 2;
                    } else if (color[index] == color[num]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
