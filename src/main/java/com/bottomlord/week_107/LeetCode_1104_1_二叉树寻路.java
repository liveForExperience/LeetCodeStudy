package com.bottomlord.week_107;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/7/29 8:16
 */
public class LeetCode_1104_1_二叉树寻路 {
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> pathInZigZagTree(int label) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(1);
        queue.offer(root);

        boolean right = true;
        while (!queue.isEmpty()) {
            int curSize = queue.size();
            int nextSize = curSize * 2;
            Integer index = null;
            while (curSize > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                int val = node.val;
                if (index == null) {
                    index = right ? val + curSize + nextSize - 1 : val + 1;
                }

                if (right) {
                    node.left = new TreeNode(index--);
                    node.right = new TreeNode(index--);

                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    node.left = new TreeNode(index++);
                    node.right = new TreeNode(index++);

                    queue.offer(node.left);
                    queue.offer(node.right);
                }

                curSize--;
            }

            index = right ? ++index : --index;

            if (index >= label) {
                break;
            }

            right = !right;
        }

        dfs(root, label, new LinkedList<>());
        return ans;
    }

    private boolean dfs(TreeNode node, int target, LinkedList<Integer> list) {
        if (node == null) {
            return false;
        }

        if (node.val == target) {
            list.add(node.val);
            ans = list;
            return true;
        }

        list.addLast(node.val);
        boolean result = dfs(node.left, target, list);
        if (result) {
            return true;
        }
        list.removeLast();

        list.addLast(node.val);
        result = dfs(node.right, target, list);
        if (result) {
            return true;
        }
        list.removeLast();

        return false;
    }

    class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
