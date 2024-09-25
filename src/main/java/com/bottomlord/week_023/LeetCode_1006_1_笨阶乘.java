package com.bottomlord.week_023;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class LeetCode_1006_1_笨阶乘 {
    public int clumsy(int N) {
        Stack<Integer> num = new Stack<>();
        Queue<Character> operator = new ArrayDeque<>();

        char[] operators = new char[]{'*', '/', '+', '-'};
        for (int i = 0; i < N - 1; i++) {
            operator.offer(operators[i % 4]);
        }

        for (int i = N; i >= 1; i--) {
            if (i == N) {
                num.push(i);
            } else {
                Character op = operator.poll();
                if (op == null) {
                    return 0;
                }

                if (op == '*') {
                    num.push(num.pop() * i);
                    continue;
                }

                if (op == '/') {
                    num.push(num.pop() / i);
                    continue;
                }

                if (op == '+' || op == '-') {
                    operator.offer(op);
                    num.push(i);
                }
            }
        }

        Stack<Integer> num2 = new Stack<>();
        while (!num.isEmpty()) {
            num2.push(num.pop());
        }

        while (num2.size() > 1) {
            int first = num2.pop();
            int second = num2.pop();
            Character op = operator.poll();
            if (op == null) {
                return 0;
            }

            if ('+' == op) {
                num2.push(first + second);
            }

            if ('-' == op) {
                num2.push(first - second);
            }
        }

        return num2.pop();
    }
}
