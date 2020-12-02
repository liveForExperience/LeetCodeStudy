package com.bottomlord.week_073;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/12/2 8:57
 */
public class LeetCode_364_1_加权嵌套序列和II {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new ArrayDeque<>();
        for (NestedInteger nestedInteger : nestedList) {
            queue.offer(nestedInteger);
        }
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int count = queue.size();
            int sum = 0;
            while (count-- > 0) {
                NestedInteger nestedInteger = queue.poll();
                if (nestedInteger == null) {
                    continue;
                }

                if (nestedInteger.isInteger()) {
                    sum += nestedInteger.getInteger();
                    continue;
                }

                List<NestedInteger> nestedIntegers = nestedInteger.getList();
                for (NestedInteger element : nestedIntegers) {
                    queue.offer(element);
                }
            }

            list.add(sum);
        }

        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += list.get(i) * (list.size() - i);
        }
        return ans;
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }
}
