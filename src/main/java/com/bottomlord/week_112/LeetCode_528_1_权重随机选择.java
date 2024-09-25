package com.bottomlord.week_112;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chen yue
 * @date 2021-08-30 08:34:35
 */
public class LeetCode_528_1_权重随机选择 {
    class Solution {
        private List<Integer> list;
        public Solution(int[] w) {
            list = new ArrayList<>();
            for (int i = 0; i < w.length; i++) {
                int count = w[i];
                while (count-- > 0) {
                    list.add(i);
                }
            }
        }

        public int pickIndex() {
            return list.get((int) (Math.random() * list.size()) + 1);
        }
    }
}
