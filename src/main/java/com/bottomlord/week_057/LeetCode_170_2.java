package com.bottomlord.week_057;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/7 20:11
 */
public class LeetCode_170_2 {
    class TwoSum {
        private Map<Integer, Integer> map;
        public TwoSum() {
            this.map = new HashMap<>();
        }

        public void add(int number) {
            this.map.put(number, map.getOrDefault(number, 0) + 1);
        }

        public boolean find(int value) {
            for (Integer num : map.keySet()) {
                int left = value - num;
                if (left != num) {
                    if (this.map.containsKey(left)) {
                        return true;
                    }
                } else {
                    if (map.getOrDefault(left, 0) > 1) {
                        return true;
                    }
                }
            }

            return false;
        }
    }
}
