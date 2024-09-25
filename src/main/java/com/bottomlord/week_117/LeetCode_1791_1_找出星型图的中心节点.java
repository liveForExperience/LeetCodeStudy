package com.bottomlord.week_117;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2021-10-10 11:44:45
 */
public class LeetCode_1791_1_找出星型图的中心节点 {
    public int findCenter(int[][] edges) {
        Set<Integer> set = new HashSet<>();
        for (int[] edge : edges) {
            if (!set.add(edge[0])) {
                return edge[0];
            }

            if (!set.add(edge[1])) {
                return edge[1];
            }
        }

        return 0;
    }
}
