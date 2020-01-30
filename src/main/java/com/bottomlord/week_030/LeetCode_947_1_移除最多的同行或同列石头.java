package com.bottomlord.week_030;

import com.bottomlord.DSU;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/1/29 19:15
 */
public class LeetCode_947_1_移除最多的同行或同列石头 {
    public int removeStones(int[][] stones) {
        int len = stones.length;
        DSU dsu = new DSU(20000);
        for (int[] stone : stones) {
            dsu.union(stone[0], stone[1] + 10000);
        }

        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            set.add(dsu.find(stone[0]));
        }

        return len - set.size();
    }
}
