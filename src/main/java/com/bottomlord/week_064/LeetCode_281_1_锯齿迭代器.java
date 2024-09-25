package com.bottomlord.week_064;

import java.util.List;

/**
 * @author ChenYue
 * @date 2020/9/26 21:31
 */
public class LeetCode_281_1_锯齿迭代器 {
    class ZigzagIterator {
        private int[] arr;
        private int i = 0;
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            arr = new int[v1.size() + v2.size()];
            int i1 = 0, i2 = 0, i = 0;
            while (i1 < v1.size() || i2 < v2.size()) {
                if (i1 < v1.size()) {
                    arr[i++] = v1.get(i1++);
                }

                if (i2 < v2.size()) {
                    arr[i++] = v2.get(i2++);
                }
            }
        }

        public int next() {
            return arr[i++];
        }

        public boolean hasNext() {
            return i < arr.length;
        }
    }
}
