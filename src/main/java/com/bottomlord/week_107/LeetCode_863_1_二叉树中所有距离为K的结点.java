package com.bottomlord.week_107;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/7/28 8:24
 */
public class LeetCode_863_1_二叉树中所有距离为K的结点 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k == 0) {
            ans.add(target.val);
        } else {
            dfs(root, target, ans, k);
        }
        return ans;
    }

    private int dfs(TreeNode node, TreeNode target, List<Integer> ans, int k) {
        if (node == null) {
            return -1;
        }

        if (node == target) {
            dfs2(node.left, 0, ans, k);
            dfs2(node.right, 0, ans, k);
            return 0;
        }

        int left = dfs(node.left, target, ans, k) + 1,
            right = dfs(node.right, target, ans, k) + 1;

        if (left > 0) {
            if (left == k) {
                ans.add(node.val);
            }
            dfs2(node.right, left, ans, k);
            return left;
        } else if (right > 0) {
            if (right == k) {
                ans.add(node.val);
            }
            dfs2(node.left, right, ans, k);
            return right;
        } else {
            return -1;
        }
    }

    private void dfs2(TreeNode node, int distance, List<Integer> ans, int k) {
        if (node == null || distance + 1 > k) {
            return;
        }

        if (distance + 1 == k) {
            ans.add(node.val);
        }

        dfs2(node.left, distance + 1, ans, k);
        dfs2(node.right, distance + 1, ans, k);
    }
}
