package com.bottomlord.week_163;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-08-27 11:42:21
 */
public class LeetCode_662_1_二叉树最大宽度 {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<WrapNode> queue = new ArrayDeque<>();
        queue.offer(new WrapNode(root, 0));
        int ans = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count-- > 0) {
                WrapNode wrapNode = queue.poll();
                if (wrapNode == null) {
                    continue;
                }

                TreeNode node = wrapNode.node;
                list.add(wrapNode.index);

                if (node.left != null) {
                    queue.offer(new WrapNode(node.left, wrapNode.index * 2));
                }

                if (node.right != null) {
                    queue.offer(new WrapNode(node.right, wrapNode.index * 2 + 1));
                }
            }

            ans = Math.max(ans, list.get(list.size() - 1) - list.get(0) + 1);
        }

        return ans;
    }

    private static class WrapNode {
        private TreeNode node;
        private int index;

        public WrapNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
