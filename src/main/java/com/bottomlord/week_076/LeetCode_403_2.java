package com.bottomlord.week_076;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/12/25 9:02
 */
public class LeetCode_403_2 {
    int[] diffs = new int[]{-1,0,1};

    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            if (i == 0) {
                map.put(stones[0], new HashSet<Integer>(){{this.add(0);}});
            } else {
                map.put(stones[i], new HashSet<>());
            }
        }

        for (int i = 0; i < stones.length - 1; i++) {
            Set<Integer> values = map.get(stones[i]);

            for (int value : values) {
                for (int diff : diffs) {
                    if (value + diff <= 0) {
                        continue;
                    }

                    if (map.containsKey(stones[i] + value + diff)) {
                        map.get(stones[i] + value + diff).add(value + diff);
                    }
                }
            }
        }

        return !map.get(stones[stones.length - 1]).isEmpty();
    }
}
