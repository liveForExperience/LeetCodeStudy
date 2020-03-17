package com.bottomlord.week_037;

import com.bottomlord.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/3/17 8:55
 */
public class Interview_0201_1_移除重复节点 {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode node = head, fake = new ListNode(0), pre = fake;
        fake.next = head;
        Set<Integer> set = new HashSet<>();
        while (node != null) {
            if (!set.contains(node.val)) {
                set.add(node.val);
            } else {
                pre.next = node.next;
                pre = node;
                node = node.next;
            }
        }

        return fake.next;
    }
}
