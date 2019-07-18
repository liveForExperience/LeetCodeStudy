package com.bottomlord.week_2;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/18 13:25
 */
public class LeetCode_965_3 {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        int val = root.val;
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != val) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return true;
    }
}
