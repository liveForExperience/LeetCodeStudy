package com.bottomlord.week_070;

import java.util.LinkedList;

public class LeetCode_402_1_移掉k位数字 {
    public String removeKdigits(String num, int k) {
        LinkedList<Character> queue = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);
            while (!queue.isEmpty() && k > 0 && queue.peekLast() > digit) {
                queue.pollLast();
                k--;
            }
            queue.offer(digit);
        }

        for (int i = 0; i < k; i++) {
            queue.pollLast();
        }

        StringBuilder sb = new StringBuilder();
        boolean zeroStart = true;
        while (!queue.isEmpty()) {
            char digit = queue.pollFirst();
            if (zeroStart && digit == '0') {
                continue;
            }

            zeroStart = false;
            sb.append(queue.pollFirst());
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
