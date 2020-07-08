package com.bottomlord.week_053;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/7/8 8:18
 */
public class LeetCode_117_1_填充每个节点的下一个右侧节点指针II {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int num = queue.size();
            Node pre = null;
            while (num-- > 0) {
                Node node = queue.poll();
                if (node == null) {
                    pre = null;
                    continue;
                }

                if (pre != null) {
                    pre.next = node;
                }

                pre = node;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }
}
