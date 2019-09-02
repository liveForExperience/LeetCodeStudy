package com.bottomlord.week_005;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_888_1_公平的糖果交换 {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        for (int num : A) {
            sumA += num;
            setA.add(num);
        }

        for (int num : B) {
            sumB += num;
            setB.add(num);
        }

        int diff = (sumA - sumB) / 2;

        for (int num : setA) {
            if (setB.contains(num - diff)) {
                return new int[]{num, num - diff};
            }
        }

        return new int[2];
    }
}
