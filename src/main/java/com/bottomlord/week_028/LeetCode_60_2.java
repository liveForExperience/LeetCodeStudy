package com.bottomlord.week_028;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/16 16:37
 */
public class LeetCode_60_2 {
    public String getPermutation(int n, int k) {
        k--;

        int[] factorial = new int[n];
        factorial[0] = 1;

        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            sb.append(list.remove(index));
            k -= index * factorial[i];
        }

        return sb.toString();
    }
}