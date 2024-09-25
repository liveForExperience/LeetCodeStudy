package com.bottomlord.week_003;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/24 11:00
 */
public class LeetCode_876_1_链表的中间节点 {
    public ListNode middleNode(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        return list.get(list.size() / 2);
    }
}
