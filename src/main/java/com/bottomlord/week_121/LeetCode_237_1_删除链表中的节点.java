package com.bottomlord.week_121;

import com.bottomlord.ListNode;

/**
 * @author chen yue
 * @date 2021-11-02 08:35:09
 */
public class LeetCode_237_1_删除链表中的节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
