package com.bottomlord.week_017;

import com.bottomlord.ListNode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_817_1_链表组件 {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int num : G) {
            set.add(num);
        }

        int ans = 0;
        ListNode node = head;
        while (node != null) {
            boolean flag = false;
            while (node != null && set.contains(node.val)) {
                node = node.next;
                flag = true;
            }

            if (flag) {
                ans++;
            }

            if (node == null) {
                break;
            }

            node = node.next;
        }

        return ans;
    }
}
