package com.bottomlord.week_196;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-04-15 13:58:05
 */
public class LeetCode_1042_1_不邻接植花 {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int x = path[0] - 1, y = path[1] - 1;
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(1);
            set.add(2);
            set.add(3);
            set.add(4);

            List<Integer> list = graph.get(i);
            for (Integer index : list) {
                set.remove(ans[index]);
            }

            ans[i] = set.iterator().next();
        }

        return ans;
    }
}
