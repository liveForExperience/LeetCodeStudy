package com.bottomlord.week_024;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeetCode_623_1_在二叉树中增加一行 {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        int level = 0;
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            if (++level == d - 1) {
                while (count-- > 0) {
                    TreeNode node = queue.poll();
                    if (node == null) {
                        continue;
                    }

                    TreeNode left = node.left, right = node.right, leftV = new TreeNode(v), rightV = new TreeNode(v);

                    node.left = leftV;
                    node.right = rightV;
                    leftV.left = left;
                    rightV.right = right;

                    if (left != null) {
                        queue.offer(left);
                    }

                    if (right != null) {
                        queue.offer(right);
                    }
                }
            } else {
                while (count-- > 0) {
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
        }

        return root;
    }
}
