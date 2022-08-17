package com.bottomlord.week_162;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-08-17 16:43:20
 */
public class LeetCode_1302_1_层数最深叶子节点的和 {
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size(), cur = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                cur += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            sum = cur;
        }

        return sum;
    }
}
