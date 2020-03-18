package com.bottomlord.week_037;

import com.bottomlord.ListNode;

/**
 * @author ThinkPad
 * @date 2020/3/18 8:45
 */
public class Interview_0203_1_删除中间节点 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
