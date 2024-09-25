package com.bottomlord.week_171;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-10-17 06:37:36
 */
public class LeetCode_904_1_水果成篮 {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;

        for (int l = 0, r = 0; r < fruits.length; r++) {
            int fruit = fruits[r];
            map.put(fruit, map.getOrDefault(fruit, 0) + 1);

            if (map.size() > 2) {
                while (l <= r && map.size() > 2) {
                    map.put(fruits[l], map.get(fruits[l]) - 1);
                    if (map.get(fruits[l]) == 0) {
                        map.remove(fruits[l]);
                    }
                    l++;
                }
            }

            max = Math.max(max, r - l + 1);
        }

        return max;
    }
}
