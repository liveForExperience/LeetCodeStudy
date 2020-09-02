package com.bottomlord.week_061;

import java.util.NoSuchElementException;

/**
 * @author ChenYue
 * @date 2020/9/2 8:42
 */
public class LeetCode_251_2 {
    class Vector2D {
        private int[][] v;
        private int r, c;
        public Vector2D(int[][] v) {
            this.v = v;
            this.r = 0;
            this.c = 0;
        }

        public int next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return v[r][c++];
        }

        public boolean hasNext() {
            while (r < v.length && c == v[r].length) {
                c = 0;
                r++;
            }

            return r < v.length;
        }
    }
}
