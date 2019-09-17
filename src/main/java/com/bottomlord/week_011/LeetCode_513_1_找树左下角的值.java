package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_513_1_找树左下角的值 {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int left = root.val;

        while (!queue.isEmpty()) {
            TreeNode leftNode = queue.poll();
            left = leftNode.val;
            int size = queue.size();

            if (leftNode.left != null) {
                queue.offer(leftNode.left);
            }

            if (leftNode.right != null) {
                queue.offer(leftNode.right);
            }
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return left;
    }
}
