package com.bottomlord.week_071;

import java.util.List;

/**
 * @author ChenYue
 * @date 2020/11/17 17:06
 */
public class LeetCode_339_1_嵌套列表权重和 {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for (NestedInteger nestedInteger : nestedList) {
            sum += sum(nestedInteger, 1);
        }

        return sum;
    }

    private int sum(NestedInteger nestedInteger, int depth) {
        if (nestedInteger == null) {
            return 0;
        }

        if (nestedInteger.isInteger()) {
            return nestedInteger.getInteger() * depth;
        }

        List<NestedInteger> nestedIntegers = nestedInteger.getList();
        int sum = 0;
        for (NestedInteger ni : nestedIntegers) {
            sum += sum(ni, depth + 1);
        }

        return sum;
    }
}