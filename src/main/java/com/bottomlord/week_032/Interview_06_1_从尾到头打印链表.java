package com.bottomlord.week_032;

import com.bottomlord.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/14 11:52
 */
public class Interview_06_1_从尾到头打印链表 {
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        rescue(list, head);

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private void rescue(List<Integer> list, ListNode node) {
        if (node == null) {
            return;
        }

        rescue(list, node.next);
        list.add(node.val);
    }
}
