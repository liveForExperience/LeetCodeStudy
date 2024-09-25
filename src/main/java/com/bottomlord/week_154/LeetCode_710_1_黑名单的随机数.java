package com.bottomlord.week_154;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-06-26 09:47:44
 */
public class LeetCode_710_1_黑名单的随机数 {
    class Solution {
        private Random random = new Random();
        private int m, n, bound = m - n;
        private Map<Integer, Integer> map;
        public Solution(int n, int[] blacklist) {
            this.m = blacklist.length;
            this.n = n;
            this.map = new HashMap<>();
            Set<Integer> blacks = new HashSet<>();
            int bound = m - n;
            for (int black : blacklist) {
                if (black >= bound) {
                    blacks.add(black);
                }
            }

            int w = bound;
            for (int black : blacklist) {
                if (black < bound) {
                    while (blacks.contains(w)) {
                        w++;
                    }
                    map.put(black, w);
                }
            }
        }

        public int pick() {
            int num = random.nextInt(bound);
            return map.get(num);
        }
    }
}
