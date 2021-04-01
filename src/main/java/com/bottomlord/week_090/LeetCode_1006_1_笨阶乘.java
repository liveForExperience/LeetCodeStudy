package com.bottomlord.week_090;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ChenYue
 * @date 2021/4/1 8:24
 */
public class LeetCode_1006_1_笨阶乘 {
    public int clumsy(int N) {
        Stack<Integer> nums1 = new Stack<>();
        Queue<Character> operators = new ArrayDeque<>();
        char[] operator = new char[]{'*', '/', '+', '-'};
        for (int i = 0; i < N - 1; i++) {
            operators.offer(operator[i % 4]);
        }

        for (int i = N; i >= 1; i--) {
            if (i == N) {
                nums1.push(i);
            } else {
                Character op = operators.poll();
                if (op == null) {
                    return 0;
                }

                if (op == '*') {
                    nums1.push(nums1.pop() * i);
                }

                if (op == '/') {
                    nums1.push(nums1.pop() / i);
                }

                if (op == '+' || op == '-') {
                    nums1.push(i);
                    operators.offer(op);
                }
            }
        }

        Stack<Integer> nums2 = new Stack<>();
        while (!nums1.isEmpty()) {
            nums2.push(nums1.pop());
        }

        while (nums2.size() > 1) {
            Integer first = nums2.pop(), second = nums2.pop();

            Character op = operators.poll();
            if (op == null) {
                return 0;
            }

            if (op == '+') {
                nums2.push(first + second);
            }

            if (op == '-') {
                nums2.push(second - first);
            }
        }

        return nums2.pop();
    }
}
