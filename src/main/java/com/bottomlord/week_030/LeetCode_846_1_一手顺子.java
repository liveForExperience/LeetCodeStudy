package com.bottomlord.week_030;

import java.util.TreeMap;

/**
 * @author ThinkPad
 * @date 2020/1/31 18:03
 */
public class LeetCode_846_1_一手顺子 {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (map.size() > 0) {
            int num = map.firstKey();
            for (int i = num; i < num + W; i++) {
                if (!map.containsKey(i)) {
                    return false;
                }
                int count = map.get(i);
                if (count == 1) {
                    map.remove(i);
                } else {
                    map.replace(i, count - 1);
                }
            }
        }

        return true;
    }
}
