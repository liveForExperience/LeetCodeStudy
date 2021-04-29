package com.bottomlord.week_094;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/4/29 8:26
 */
public class LeetCode_403_1_青蛙过河 {
    private int[] diffs = new int[]{-1, 0, 1};

    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<>());
            if (i == 0) {
                map.get(stones[i]).add(0);
            }
        }

        for (int stone : stones) {
            Set<Integer> distances = map.get(stone);

            for (Integer distance : distances) {
                for (int diff : diffs) {
                    if (distance + diff <= 0) {
                        continue;
                    }

                    if (map.containsKey(stone + distance + diff)) {
                        map.get(stone + distance + diff).add(distance + diff);
                    }
                }
            }
        }

        return !map.get(stones[stones.length - 1]).isEmpty();
    }
}
