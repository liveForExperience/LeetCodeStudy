package com.bottomlord.week_117;

import java.util.Iterator;

/**
 * @author chen yue
 * @date 2021-10-05 20:39:56
 */
public class LeetCode_284_1_顶端迭代器 {
    class PeekingIterator implements Iterator<Integer> {
        private Integer peek;
        private Iterator<Integer> iterator;
        public PeekingIterator(Iterator<Integer> iterator) {
            this.iterator = iterator;
        }

        public Integer peek() {
            if (!hasNext()) {
                return null;
            }

            if (peek == null) {
                peek = next();
                return peek;
            }

            return peek;
        }

        @Override
        public Integer next() {
            if (peek != null) {
                Integer ans = peek;
                peek = null;
                return ans;
            }

            if (!hasNext()) {
                return null;
            }

            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            if (peek != null) {
                return true;
            }
            return iterator.hasNext();
        }
    }
}
