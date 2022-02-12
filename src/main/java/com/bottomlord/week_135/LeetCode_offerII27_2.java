package com.bottomlord.week_135;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2022-02-12 13:42:11
 */
public class LeetCode_offerII27_2 {
    public boolean isPalindrome(ListNode head) {
        ListNode fake = new ListNode(0), slow = fake, fast = fake;
        fake.next = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode tail = reverse(slow.next);

        while (head != tail && head != null && tail != null) {
            if (head.val != tail.val) {
                return false;
            }

            head = head.next;
            tail = tail.next;
        }

        return true;
    }

    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        return pre;
    }
}
