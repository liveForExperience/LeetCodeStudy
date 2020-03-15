package com.bottomlord.contest_20200315;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/15 16:06
 */
public class Contest_3_1_将二叉搜索树再平衡 {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        return build(list.size() / 2, 0, list.size(), list);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }

    private TreeNode build(int mid, int head, int tail, List<Integer> list) {
        if (head >= tail) {
            return null;
        }

        TreeNode node = new TreeNode(list.get(mid));
        node.left = build(head + (mid - head) / 2, head, mid, list);
        node.right = build(mid + 1 + (tail - mid - 1) / 2, mid + 1, tail, list);
        return node;
    }
}
