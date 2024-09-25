package com.bottomlord.week_031;

import com.bottomlord.DSU;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/2/3 15:28
 */
public class LeetCode_1319_2 {
    public int makeConnected(int n, int[][] connections) {
        if (n - 1 > connections.length) {
            return -1;
        }

        DSU dsu = new DSU(n);
        for (int[] connection : connections) {
            dsu.union(connection[0], connection[1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i == dsu.find(i)) {
                count++;
            }
        }

        return count - 1;
    }
}