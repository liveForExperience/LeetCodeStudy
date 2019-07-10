package com.bottomlord.week_1;

import com.bottomlord.ListNode;

/**
 * @author LiveForExperience
 * @date 2019/7/8 18:08
 */
public class LeetCode_237_1_删除链表中节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
