package com.bottomlord.week_037;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/18 8:35
 */
public class Interview_0202_1_返回倒数第k个节点 {
    public int kthToLast(ListNode head, int k) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            list.add(node.val);
            node = node.next;
        }

        return list.get(len - k);
    }
}
