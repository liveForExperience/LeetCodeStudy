package com.bottomlord.week_081;

import com.bottomlord.Node;
import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2021/1/25 14:17
 */
public class LeetCode_431_1_将N叉树编码成二叉树 {
    static class Codec {
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }

            Queue<Node> queue = new ArrayDeque<>();
            queue.offer(root);

            Queue<TreeNode> treeQueue = new ArrayDeque<>();
            TreeNode rootTreeNode = new TreeNode(root.val);
            treeQueue.offer(rootTreeNode);

            while (!queue.isEmpty()) {
                Node node = queue.poll();
                TreeNode treeNode = treeQueue.poll();

                if (node == null || treeNode == null) {
                    continue;
                }

                List<Node> children = node.children;
                if (children == null || children.size() == 0) {
                    continue;
                }

                TreeNode fakeTreeNode = new TreeNode(0);
                TreeNode pre = fakeTreeNode;

                for (Node child : children) {
                    TreeNode childTreeNode = new TreeNode(child.val);
                    if (child.children != null && child.children.size() != 0) {
                        queue.offer(child);
                        treeQueue.offer(childTreeNode);
                    }

                    pre.right = childTreeNode;
                    pre = childTreeNode;
                }

                treeNode.left = fakeTreeNode.right;
            }

            return rootTreeNode;
        }

        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }

            return dfs(root, null);
        }

        private Node dfs(TreeNode treeNode, List<Node> bros) {
            if (treeNode == null) {
                return null;
            }

            Node node = new Node(treeNode.val);

            if (bros != null) {
                bros.add(node);
            }

            List<Node> children = new ArrayList<>();
            dfs(treeNode.left, children);
            node.children = children;

            dfs(treeNode.right, bros);

            return node;
        }
    }
}
