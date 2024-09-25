package com.bottomlord.week_094;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/30 10:31
 */
public class LeetCode_554_1_砖墙 {
    public int leastBricks(List<List<Integer>> wall) {
        int rowLen = wall.get(0).stream().mapToInt(x -> x).sum();

        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int sum = 0;
            for (Integer num : list) {
                sum += num;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max && entry.getKey() < rowLen) {
                max = entry.getValue();
            }
        }

        return wall.size() - max;
    }
}
