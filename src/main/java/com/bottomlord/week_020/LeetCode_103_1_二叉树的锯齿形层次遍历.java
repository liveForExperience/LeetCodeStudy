package com.bottomlord.week_020;

import com.bottomlord.TreeNode;

import java.util.*;

public class LeetCode_103_1_二叉树的锯齿形层次遍历 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            List<Integer> inner = new ArrayList<>();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                inner.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(inner);
        }

        boolean reserve = false;
        for (List<Integer> list : ans) {
            if (reserve) {
                Collections.reverse(list);
            }

            reserve = !reserve;
        }

        return ans;
    }
}
