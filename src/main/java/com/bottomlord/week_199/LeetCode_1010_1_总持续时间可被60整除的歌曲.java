package com.bottomlord.week_199;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-05-07 10:44:16
 */
public class LeetCode_1010_1_总持续时间可被60整除的歌曲 {
    public int numPairsDivisibleBy60(int[] times) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < times.length; i++) {
            times[i] = times[i] % 60;
            indexMap.put(times[i], 0);
            map.computeIfAbsent(times[i], x -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (int i = 0; i < times.length; i++) {
            int target = (60 - times[i]) % 60;
            if (!map.containsKey(target)) {
                continue;
            }

            List<Integer> indexes = map.get(target);
            for (int j = indexMap.get(target); j < indexes.size(); j++) {
                if (indexes.get(j) > i) {
                    indexMap.put(target, j);
                    ans += indexes.size() - j;
                    break;
                }
            }
        }

        return ans;
    }
}
