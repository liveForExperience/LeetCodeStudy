package com.bottomlord.week_001;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/14 14:49
 */
public class LeetCode_905_1_按奇偶排序数组 {
    public int[] sortArrayByParity(int[] A) {
        Stack<Integer> odds = new Stack<>();
        Stack<Integer> evens = new Stack<>();

        for (int num: A) {
            if (num % 2 == 0) {
                evens.push(num);
            } else {
                odds.push(num);
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (!evens.isEmpty()) {
                A[i] = evens.pop();
            } else {
                A[i] = odds.pop();
            }
        }

        return A;
    }
}
