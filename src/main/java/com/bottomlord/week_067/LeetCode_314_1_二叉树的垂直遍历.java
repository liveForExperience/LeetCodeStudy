package com.bottomlord.week_067;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/10/23 8:59
 */
public class LeetCode_314_1_二叉树的垂直遍历 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        Queue<WrapTreeNode> queue = new ArrayDeque<>();
        queue.offer(new WrapTreeNode(root, 0));
        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                WrapTreeNode wrapTreeNode = queue.poll();
                if (wrapTreeNode == null) {
                    continue;
                }

                List<Integer> indexList = map.getOrDefault(wrapTreeNode.index, new ArrayList<>());
                TreeNode treeNode = wrapTreeNode.treeNode;
                indexList.add(treeNode.val);
                map.put(wrapTreeNode.index, indexList);

                if (treeNode.left != null) {
                    queue.offer(new WrapTreeNode(treeNode.left, wrapTreeNode.index - 1));
                }

                if (treeNode.right != null) {
                    queue.offer(new WrapTreeNode(treeNode.right, wrapTreeNode.index + 1));
                }
            }
        }

        return new ArrayList<>(map.values());
    }

    class WrapTreeNode {
        private TreeNode treeNode;
        private int index;

        public WrapTreeNode(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
}
