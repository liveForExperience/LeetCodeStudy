package com.bottomlord.week_082;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/2/1 8:34
 */
public class LeetCode_888_1_公平的糖果棒交换 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int a = Arrays.stream(A).sum(), b = Arrays.stream(B).sum(), diff = a - b;
        Set<Integer> set = new HashSet<>();
        for (int num : A) {
            set.add(num);
        }

        for (int num : B) {
            if (set.contains(diff / 2 + num)) {
                return new int[]{diff / 2 + num, num};
            }
        }

        throw new RuntimeException();
    }
}
