package com.bottomlord.week_222;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-10-13 20:04:48
 */
public class LeetCode_1488_1_避免洪水泛滥 {
    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Arrays.fill(ans, 1);
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            int lake = rains[i];
            if (lake == 0) {
                set.add(i);
                continue;
            }

            ans[i] = -1;

            if (map.containsKey(lake)) {
                Integer day = set.ceiling(map.get(lake));
                if (day == null) {
                    return new int[0];
                }

                ans[day] = lake;
                set.remove(day);
            }

            map.put(lake, i);
        }

        return ans;
    }
}
