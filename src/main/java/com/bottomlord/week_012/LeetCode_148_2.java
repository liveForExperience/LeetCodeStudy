package com.bottomlord.week_012;

import com.bottomlord.ListNode;

public class LeetCode_148_2 {
    public ListNode sortList(ListNode head) {
        ListNode node = head;
        int len = 0, l = 1;
        while (node != null) {
            node = node.next;
            len++;
        }

        ListNode res = new ListNode(0);
        res.next = head;
        while (l < len) {
            ListNode pre = res;
            node = res.next;
            while (node != null) {
                ListNode n1 = node;
                int tmpL = l;
                while (node != null && tmpL != 0) {
                    node = node.next;
                    tmpL--;
                }

                if (node == null) {
                    break;
                }

                ListNode n2 = node;
                tmpL = l;
                while (node != null && tmpL != 0) {
                    node = node.next;
                    tmpL--;
                }

                int c1 = l, c2 = l - tmpL;
                while (c1 != 0 && c2 != 0) {
                    if (n1.val < n2.val) {
                        pre.next = n1;
                        n1 = n1.next;
                        c1--;
                    } else {
                        pre.next = n2;
                        n2 = n2.next;
                        c2--;
                    }
                    pre = pre.next;
                }

                pre.next = c1 == 0 ? n2 : n1;
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }

                pre.next = node;
            }

            l *= 2;
        }

        return res.next;
    }
}