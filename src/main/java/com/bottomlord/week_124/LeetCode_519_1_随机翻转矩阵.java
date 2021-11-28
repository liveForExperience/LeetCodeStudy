package com.bottomlord.week_124;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author chen yue
 * @date 2021-11-27 23:08:56
 */
public class LeetCode_519_1_随机翻转矩阵 {
    class Solution {
        private int num, r, c;
        private Map<Integer, Integer> mapping;
        private Random random;
        public Solution(int n_rows, int n_cols) {
            num = n_rows * n_cols;
            r = n_rows;
            c = n_cols;
            mapping = new HashMap<>();
            random = new Random();
        }

        public int[] flip() {
            int index = random.nextInt(num--);
            int value = mapping.getOrDefault(index, index);
            mapping.put(index, mapping.getOrDefault(num, num));
            return new int[]{value / c, value % c};
        }

        public void reset() {
            num = r * c;
            mapping.clear();
        }
    }
}
