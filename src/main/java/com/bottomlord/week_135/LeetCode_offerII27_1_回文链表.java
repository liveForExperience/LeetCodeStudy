package com.bottomlord.week_135;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-02-12 13:36:43
 */
public class LeetCode_offerII27_1_回文链表 {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        int l = 0, r = list.size() - 1;
        while (l < r) {
            if (!Objects.equals(list.get(l++), list.get(r--))) {
                return false;
            }
        }
        return true;
    }
}
