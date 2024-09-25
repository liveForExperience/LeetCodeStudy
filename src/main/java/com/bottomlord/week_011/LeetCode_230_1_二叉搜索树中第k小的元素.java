package com.bottomlord.week_011;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_230_1_二叉搜索树中第k小的元素 {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        return list.get(k - 1);
    }

    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(list, node.left);
        list.add(node.val);
        dfs(list, node.right);
    }
}
