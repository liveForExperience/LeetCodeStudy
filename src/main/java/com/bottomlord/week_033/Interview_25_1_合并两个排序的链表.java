package com.bottomlord.week_033;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/22 12:19
 */
public class Interview_25_1_合并两个排序的链表 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        List<Integer> list = new ArrayList<>();
        ListNode node = l1;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        node = l2;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        Collections.sort(list);

        ListNode fake = new ListNode(0);
        if (list.size() == 0) {
            return null;
        }
        node = new ListNode(list.get(0));
        fake.next = node;
        for (int i = 1; i < list.size(); i++) {
            node.next = new ListNode(list.get(i));
            node = node.next;
        }

        return fake.next;
    }
}
