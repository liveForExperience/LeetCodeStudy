package com.bottomlord.week_025;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ThinkPad
 * @date 2019/12/26 20:48
 */
public class LeetCode_1019_2 {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        Stack<Integer> stack = new Stack<>();
        int len = list.size();
        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            int val = list.get(i);

            while (!stack.isEmpty() && list.get(stack.peek()) < val) {
                ans[stack.pop()] = val;
            }

            stack.push(i);
        }

        return ans;
    }
}