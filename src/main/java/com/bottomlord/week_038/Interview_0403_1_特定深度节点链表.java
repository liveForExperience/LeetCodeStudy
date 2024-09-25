package com.bottomlord.week_038;

import com.bottomlord.ListNode;
import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/3/23 12:35
 */
public class Interview_0403_1_特定深度节点链表 {
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];
        }

        List<ListNode> ansList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(tree);

        while (!queue.isEmpty()) {
            int count = queue.size();
            ListNode head = new ListNode(-1), pre = head;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                ListNode cur = new ListNode(node.val);
                pre.next = cur;
                pre = cur;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ansList.add(head.next);
        }

        return ansList.toArray(new ListNode[0]);
    }
}
