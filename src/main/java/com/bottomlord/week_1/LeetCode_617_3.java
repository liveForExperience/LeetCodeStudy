package com.bottomlord.week_1;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/10 12:23
 */
public class LeetCode_617_3 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }

        Queue<TreeNode[]> queue = new ArrayDeque<>();
        TreeNode[] root = new TreeNode[]{t1, t2};
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.poll();
            if (nodes[1] == null) {
                continue;
            }

            nodes[0].val += nodes[1].val;
            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                queue.offer(new TreeNode[]{nodes[0].left, nodes[1].left});
            }

            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                queue.offer(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }

        return t1;
    }
}
