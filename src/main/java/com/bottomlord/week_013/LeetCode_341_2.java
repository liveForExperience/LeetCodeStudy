package com.bottomlord.week_013;

import com.bottomlord.INestedInteger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_341_2 {
    public class NestedIterator implements Iterator<Integer> {
        private LinkedList<Iterator<INestedInteger>> stack = new LinkedList<>();
        private boolean flag = false;
        private Integer num;
        public NestedIterator(List<INestedInteger> nestedList) {
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
                Iterator<INestedInteger> iterator = stack.peekFirst();
                if (iterator.hasNext()) {
                    INestedInteger INestedInteger = iterator.next();
                    if (INestedInteger == null) {
                        continue;
                    }

                    if (INestedInteger.isInteger()) {
                        flag = true;
                        num = INestedInteger.getInteger();
                    } else {
                        stack.offerFirst(INestedInteger.getList().iterator());
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
