package com.bottomlord.week_061;

import java.util.Stack;

/**
 * @author ChenYue
 * @date 2020/9/6 16:36
 */
public class LeetCode_255_2 {
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 1) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(preorder[0]);
        int min = Integer.MIN_VALUE, i = 1;

        while (i < preorder.length) {
            if (min > preorder[i]) {
                return false;
            }

            if (!stack.isEmpty() && preorder[i] > stack.peek()) {
                min = stack.pop();
            } else {
                stack.push(preorder[i]);
                i++;
            }
        }

        return true;
    }
}