package com.bottomlord.week_157;

import java.util.Stack;

/**
 * @author chen yue
 * @date 2022-07-13 13:02:37
 */
public class LeetCode_735_1_行星碰撞 {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty()) {
                stack.push(asteroid);
                continue;
            }

            boolean push = false;
            while (!stack.isEmpty()) {
                Integer peek = stack.peek();
                if (peek * asteroid < 0 && peek > 0) {
                    if (peek < -asteroid) {
                        stack.pop();
                        push = true;
                    } else if (peek == -asteroid) {
                        stack.pop();
                        push = false;
                        break;
                    } else {
                        push = false;
                        break;
                    }
                } else {
                    push = true;
                    break;
                }
            }

            if (push) {
                stack.push(asteroid);
            }
        }

        int[] ans = new int[stack.size()];
        int index = 0;
        for (Integer num : stack) {
            ans[index++] = num;
        }
        return ans;
    }
}
