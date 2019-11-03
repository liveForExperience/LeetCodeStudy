package com.bottomlord.week_018;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode_1110_1_删点成林 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (Integer val : to_delete) {
            set.add(val);
        }

        TreeNode node = dfs(root, set, ans);
        if (node != null) {
            ans.add(node);
        }

        return ans;
    }

    private TreeNode dfs(TreeNode node, Set<Integer> toDelete, List<TreeNode> ans) {
        if (node == null) {
            return null;
        }

        node.left = dfs(node.left, toDelete, ans);
        node.right = dfs(node.right, toDelete, ans);

        if (toDelete.contains(node.val)) {
            if (node.left != null) {
                ans.add(node.left);
            }

            if (node.right != null) {
                ans.add(node.right);
            }

            return null;
        }
        return node;
    }
}
