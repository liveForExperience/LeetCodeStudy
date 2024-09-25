package com.bottomlord.week_037;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ThinkPad
 * @date 2020/3/19 9:04
 */
public class Interview_0206_1_回文链表 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!Objects.equals(list.get(left), list.get(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
