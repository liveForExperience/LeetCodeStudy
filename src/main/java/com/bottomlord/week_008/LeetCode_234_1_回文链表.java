package com.bottomlord.week_008;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_234_1_回文链表 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
