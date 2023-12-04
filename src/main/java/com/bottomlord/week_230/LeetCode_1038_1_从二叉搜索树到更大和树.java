package com.bottomlord.week_230;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-12-04 09:08:04
 */
public class LeetCode_1038_1_从二叉搜索树到更大和树 {
    public TreeNode bstToGst(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        if (list.isEmpty()) {
            return root;
        }

        int[] sums = new int[list.size() + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + list.get(i - 1).val;
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).val = sums[list.size()] - sums[i];
        }

        return root;
    }

    private void dfs(TreeNode node, List<TreeNode> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node);
        dfs(node.right, list);
    }
}
