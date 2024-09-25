package com.bottomlord.week_017;

import com.bottomlord.ListNode;

public class LeetCode_817_2 {
    public int numComponents(ListNode head, int[] G) {
        int[] bucket = new int[10000];
        for (int num : G) {
            bucket[num]++;
        }

        int ans = 0;
        ListNode node = head;
        while (node != null) {
            boolean flag = false;
            while (node != null && bucket[node.val] > 0) {
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