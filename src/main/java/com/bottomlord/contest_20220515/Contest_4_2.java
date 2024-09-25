package com.bottomlord.contest_20220515;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author chen yue
 * @date 2022-05-15 21:10:34
 */
public class Contest_4_2 {
    static class CountIntervals {
        private TreeMap<Integer, int[]> map;
        private int sum;

        public CountIntervals() {
            this.map = new TreeMap<>();
            this.sum = 0;
        }

        public void add(int L, int R) {
            Map.Entry<Integer, int[]> entry = map.floorEntry(R);
            int l = L, r = R;
            while (entry != null && l <= entry.getValue()[1]) {
                l = Math.min(l, entry.getValue()[0]);
                r = Math.max(r, entry.getValue()[1]);
                map.remove(entry.getKey());
                sum -= entry.getValue()[1] - entry.getValue()[0] + 1;
                entry = map.floorEntry(R);
            }

            map.put(l, new int[]{l, r});
            sum += r - l + 1;
        }

        public int count() {
            return sum;
        }
    }
}
