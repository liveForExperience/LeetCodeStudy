package com.bottomlord.week_092;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/13 11:22
 */
public class LeetCode_519_2 {
    class Solution {
        private int num;
        private int row;
        private int col;
        private Map<Integer, Integer> map;
        private Random random;
        public Solution(int n_rows, int n_cols) {
            this.num = n_cols * n_rows;
            this.row = n_rows;
            this.col = n_cols;
            this.map = new HashMap<>();
            this.random = new Random();
        }

        public int[] flip() {
            int r = random.nextInt(num--);
            int index = map.getOrDefault(r, r);
            map.put(r, map.getOrDefault(num, num));
            return new int[]{index / col, index % col};
        }

        public void reset() {
            this.map.clear();
            this.num = col * row;
        }
    }
}
