package com.bottomlord.week_231;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author chen yue
 * @date 2023-12-16 22:45:01
 */
public class LeetCode_2276_1_统计区间中的整数数目 {
    class CountIntervals {
        private TreeMap<Integer, Integer> treeMap;
        private int cnt;

        public CountIntervals() {
            this.treeMap = new TreeMap<>();
            this.cnt = 0;
        }

        public void add(int left, int right) {
            while (!treeMap.isEmpty() && treeMap.floorEntry(right) != null) {
                Map.Entry<Integer, Integer> interval = treeMap.floorEntry(right);
                if (interval.getValue() < left) {
                    break;
                }

                cnt -= interval.getValue() - interval.getKey() + 1;
                left = Math.min(interval.getKey(), left);
                right = Math.max(interval.getValue(), right);
                treeMap.remove(interval.getKey());
            }

            cnt += right - left + 1;
            treeMap.put(left, right);
        }

        public int count() {
            return cnt;
        }
    }
}
