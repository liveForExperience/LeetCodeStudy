package com.bottomlord.week_002;

import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/20 17:15
 */
public class LeetCode_682_1_棒球比赛 {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if ("C".equals(op)) {
                stack.pop();
                continue;
            }

            if ("D".equals(op)) {
                stack.push(stack.peek() * 2);
                continue;
            }

            if ("+".equals(op)) {
                int pre = stack.pop();
                int pre2 = stack.peek();
                stack.push(pre);
                stack.push(pre + pre2);
                continue;
            }

            stack.push(Integer.parseInt(op));
        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}
