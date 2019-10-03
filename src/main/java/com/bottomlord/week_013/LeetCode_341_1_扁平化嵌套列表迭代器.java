package com.bottomlord.week_013;

import com.bottomlord.NestedInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeetCode_341_1_扁平化嵌套列表迭代器 {
    public class NestedIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        public NestedIterator(List<NestedInteger> nestedList) {
            List<Integer> list = new ArrayList<>();
            recurse(list, nestedList);
            iterator = list.iterator();
        }

        private void recurse(List<Integer> list, List<NestedInteger> nestedList) {
            for (NestedInteger nestedInteger : nestedList) {
                if (nestedInteger.isInteger()) {
                    list.add(nestedInteger.getInteger());
                } else {
                    recurse(list, nestedInteger.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}
