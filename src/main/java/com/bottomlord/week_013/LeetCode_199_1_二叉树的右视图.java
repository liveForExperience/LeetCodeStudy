package com.bottomlord.week_013;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeetCode_199_1_二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len > 0) {
                TreeNode node = queue.poll();

                if (len == 1) {
                    ans.add(node.val);
                }
                len--;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }
}
