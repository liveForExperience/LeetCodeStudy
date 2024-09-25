package com.bottomlord.contest_20220501;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-05-01 00:34:25
 */
public class Contest_2_1_必须拿起的最小连续卡牌数 {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            int num = cards[i];
            map.computeIfAbsent(num, x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 2) {
                continue;
            }

            List<Integer> list = entry.getValue();
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i + 1) - list.get(i) < min) {
                    min = list.get(i + 1) - list.get(i);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
