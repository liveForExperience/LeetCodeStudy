package com.bottomlord.week_127;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-12-17 09:03:39
 */
public class LeetCode_911_1_在线选举 {
    class TopVotedCandidate {
        private List<int[]> list = new ArrayList<>();
        public TopVotedCandidate(int[] persons, int[] times) {
            int n = times.length, val = 0;
            Map<Integer, Integer> mapping = new HashMap<>();
            for (int i = 0; i < n; i++) {
                mapping.put(persons[i], mapping.getOrDefault(persons[i], 0) + 1);
                if (mapping.get(persons[i]) >= val) {
                    val = mapping.get(persons[i]);
                    list.add(new int[]{times[i], persons[i]});
                }
            }
        }

        public int q(int t) {
            int head = 0, tail = list.size() - 1;
            while (head < tail) {
                int mid = head + tail + 1 >> 1;
                if (list.get(mid)[0] <= t) {
                    head = mid;
                } else {
                    tail = mid - 1;
                }
            }

            return list.get(tail)[0] <= t ? list.get(tail)[1] : 0;
        }
    }
}
