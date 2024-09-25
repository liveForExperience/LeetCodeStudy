package com.bottomlord.week_022;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_116_1_填充每个节点的下一个右侧节点指针 {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();

            while (len-- > 0) {
                Node node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (len != 0) {
                    node.next = queue.peek();
                }
            }
        }

        return root;
    }
}
