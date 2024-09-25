package com.bottomlord.week_002;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/19 19:37
 */
public class LeetCode_922_1_按奇偶排序数组II {
    public int[] sortArrayByParityII(int[] A) {
        Stack<Integer> oddStack = new Stack<>();
        Stack<Integer> evenStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0 && i % 2 == 1) {
                evenStack.push(A[i]);
                if (!oddStack.isEmpty()) {
                    A[i] = oddStack.pop();
                } else {
                    indexStack.push(i);
                }
            }

            if (A[i] % 2 == 1 && i % 2 == 0) {
                oddStack.push(A[i]);
                if (!evenStack.isEmpty()) {
                    A[i] = evenStack.pop();
                } else {
                    indexStack.push(i);
                }
            }
        }

        while (!indexStack.isEmpty()) {
            int i = indexStack.pop();
            if (i % 2 == 0) {
                A[i] = evenStack.pop();
            } else {
                A[i] = oddStack.pop();
            }
        }

        return A;
    }
}
