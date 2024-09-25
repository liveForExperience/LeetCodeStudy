package com.bottomlord.week_061;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author ChenYue
 * @date 2020/9/2 8:22
 */
public class LeetCode_251_1_展开二维向量 {
    class Vector2D {
        private List<Integer> list;
        private ListIterator<Integer> iterator;
        public Vector2D(int[][] v) {
            this.list = new ArrayList<>();
            for (int[] arr : v) {
                for (int num : arr) {
                    this.list.add(num);
                }
            }
            this.iterator = this.list.listIterator();
        }

        public int next() {
            return iterator.next();
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}
