package com.bottomlord.week_159;

import com.bottomlord.TreeNode;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-07-25 09:02:14
 */
public class LeetCode_919_1_完全二叉树插入器 {
    class CBTInserter {

        private TreeNode root;
        private Queue<TreeNode> queue = new ArrayDeque<>();

        public CBTInserter(TreeNode root) {
            this.root = root;
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);

            while (!q.isEmpty()) {
                int size = q.size();

                while (size-- > 0) {
                    TreeNode node = q.poll();
                    if (node == null) {
                        continue;
                    }

                    if (node.left != null) {
                        q.offer(node.left);
                    }

                    if (node.right != null) {
                        q.offer(node.right);
                    }

                    if (node.left == null || node.right == null) {
                        queue.offer(node);
                    }
                }
            }
        }

        public int insert(int val) {
            TreeNode node = new TreeNode(val);

            if (queue.isEmpty()) {
                this.root = node;
                return 0;
            }

            TreeNode parent = queue.peek();
            if (parent.left == null) {
                parent.left = node;
            } else if (parent.right == null) {
                parent.right = node;
                queue.poll();
            }

            queue.offer(node);
            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }
}
