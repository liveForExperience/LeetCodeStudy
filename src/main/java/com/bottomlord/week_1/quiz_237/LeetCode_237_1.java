package com.bottomlord.week_1.quiz_237;

import com.bottomlord.ListNode;

/**
 * @author LiveForExperience
 * @date 2019/7/8 18:08
 */
public class LeetCode_237_1 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
