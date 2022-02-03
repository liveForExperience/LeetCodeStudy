package com.bottomlord.week_134;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-02-03 22:43:10
 */
public class LeetCode_1414_1_和为K的最少斐波那契数字数目 {
    private int count = 0;

    public int findMinFibonacciNumbers(int k) {
        recuse(k, new HashSet<>());
        return count;
    }

    private void recuse(int k, Set<Integer> set) {
        int fab = fab(k, set);
        count++;
        if (fab == k) {
            return;
        }

        if (set.contains(k - fab)) {
            count++;
            return;
        }

        recuse(k - fab, set);
    }

    private int fab(int k, Set<Integer> set) {
        int x = 0, z = 0, y = 1;
        while (z <= k) {
            set.add(y);
            z = x + y;
            x = y;
            y = z;
        }

        return x;
    }
}
