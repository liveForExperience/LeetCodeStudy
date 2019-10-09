package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_951_2 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        dfs(root2, list2);
        return list1.equals(list2);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node != null) {
            list.add(node.val);

            int left = node.left != null ? node.left.val : -1;
            int right = node.right != null ? node.right.val : -1;

            if (left < right) {
                dfs(node.left, list);
                dfs(node.right, list);
            } else {
                dfs(node.right, list);
                dfs(node.left, list);
            }
        }
    }
}