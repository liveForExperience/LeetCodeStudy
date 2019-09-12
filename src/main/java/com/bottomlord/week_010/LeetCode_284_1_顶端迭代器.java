package com.bottomlord.week_010;

import java.util.Iterator;

public class LeetCode_284_1_顶端迭代器 {
    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer peekNum;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (peekNum != null) {
                return peekNum;
            }

            peekNum = iterator.next();
            return peekNum;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (peekNum != null) {
                int nextNum = peekNum;
                peekNum = null;
                return nextNum;
            }

            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            if (peekNum != null) {
                return true;
            }

            return iterator.hasNext();
        }
    }
}
