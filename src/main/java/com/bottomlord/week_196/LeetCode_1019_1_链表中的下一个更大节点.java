package com.bottomlord.week_196;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-04-10 17:57:06
 */
public class LeetCode_1019_1_链表中的下一个更大节点 {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int n = list.size();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = list.get(i);

            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(num);
        }

        return ans;
    }
}
