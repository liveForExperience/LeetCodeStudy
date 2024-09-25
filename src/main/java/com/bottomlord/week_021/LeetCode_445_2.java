package com.bottomlord.week_021;

import com.bottomlord.ListNode;

import java.util.Stack;

public class LeetCode_445_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> ls = getStack(l1);
        Stack<Integer> rs = getStack(l2);

        int carry = 0;
        ListNode cur = null, pre = null;
        while (!ls.isEmpty() || !rs.isEmpty()) {
            int val = carry;
            if (!ls.isEmpty()) {
                val += ls.pop();
            }
            if (!rs.isEmpty()) {
                val += rs.pop();
            }

            carry = val / 10;
            pre = cur;
            cur = new ListNode(val % 10);
            cur.next = pre;
        }

        if (carry == 1) {
            ListNode head = new ListNode(1);
            head.next = cur;
            return head;
        }

        return cur;
    }

    private Stack<Integer> getStack(ListNode node) {
        Stack<Integer> stack = new Stack<>();
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        return stack;
    }
}
