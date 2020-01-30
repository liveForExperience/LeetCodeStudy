package com.bottomlord.week_030;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/30 20:40
 */
public class LeetCode_863_2 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        if (K == 0) {
            ans.add(target.val);
        } else {
            dfs(root, target, K, ans);
        }
        return ans;
    }

    private int dfs(TreeNode node, TreeNode target, int k, List<Integer> list) {
        if (node == null) {
            return -1;
        }

        if (node == target) {
            dfs2(node.left, 1, k, list);
            dfs2(node.right, 1, k, list);
            return 0;
        }

        int left = dfs(node.left, target, k, list) + 1;
        int right = dfs(node.right, target, k, list) + 1;

        if (left > 0) {
            if (left == k) {
                list.add(node.val);
            }
            dfs2(node.right, left + 1, k, list);
            return left;
        } else if (right > 0) {
            if (right == k) {
                list.add(node.val);
            }
            dfs2(node.left, right + 1, k, list);
            return right;
        } else {
            return -1;
        }
    }

    private void dfs2(TreeNode node, int dist, int k, List<Integer> list) {
        if (node == null || dist > k) {
            return;
        }

        if (dist == k) {
            list.add(node.val);
        }
        dfs2(node.left, dist + 1, k, list);
        dfs2(node.right, dist + 1, k, list);
    }
}