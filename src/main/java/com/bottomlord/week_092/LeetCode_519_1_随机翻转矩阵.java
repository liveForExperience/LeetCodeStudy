package com.bottomlord.week_092;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/4/13 8:42
 */
public class LeetCode_519_1_随机翻转矩阵 {
    class Solution {
        private int len;
        private int row;
        private int col;
        private Set<Integer> memo;
        private Random random;

        public Solution(int n_rows, int n_cols) {
            this.len = n_cols * n_rows;
            this.row = n_rows;
            this.col = n_cols;
            this.memo = new HashSet<>();
            this.random = new Random();
        }

        public int[] flip() {
            int r = random.nextInt(len);
            while (memo.contains(r)) {
                r = random.nextInt(len);
            }

            memo.add(r);
            return new int[]{r / col, r % col};
        }

        public void reset() {
            this.memo.clear();
        }
    }
}
