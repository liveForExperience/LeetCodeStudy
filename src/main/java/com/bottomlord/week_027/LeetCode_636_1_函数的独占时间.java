package com.bottomlord.week_027;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2020/1/10 8:29
 */
public class LeetCode_636_1_函数的独占时间 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        int pre = 0;
        for (String log : logs) {
            String[] strs = log.split(":");
            Integer id = Integer.valueOf(strs[0]);
            int time = Integer.parseInt(strs[2]);
            if (Objects.equals("start", strs[1])) {
                if (!stack.isEmpty()) {
                    ans[stack.peek()] += time - pre;
                }
                pre = time;
                stack.push(id);
            } else {
                ans[stack.pop()] += time - pre;
                pre = time + 1;
            }
        }

        return ans;
    }
}