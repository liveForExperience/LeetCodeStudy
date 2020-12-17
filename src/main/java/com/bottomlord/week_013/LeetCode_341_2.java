package com.bottomlord.week_013;

import com.bottomlord.INestedInteger;
import com.bottomlord.NestedInteger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_341_2 {
    public class NestedIterator implements Iterator<Integer> {
        private LinkedList<Iterator<NestedInteger>> stack = new LinkedList<>();
        private boolean flag = false;
        private Integer num;
        public NestedIterator(List<NestedInteger> nestedList) {
            stack.offer(nestedList.iterator());
        }

        @Override
        public Integer next() {
            flag = false;
            return num;
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty() && !flag) {
                Iterator<NestedInteger> iterator = stack.peekFirst();
                if (iterator.hasNext()) {
                    INestedInteger nestedInteger = iterator.next();
                    if (nestedInteger == null) {
                        continue;
                    }

                    if (nestedInteger.isInteger()) {
                        flag = true;
                        num = nestedInteger.getInteger();
                    } else {
                        stack.offerFirst(nestedInteger.getList().iterator());
                        iterator.remove();
                    }
                } else {
                    stack.pollFirst();
                }
            }

            return flag;
        }
    }
}
