package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class LeetCode_1038_1_从二叉搜索树到更大和树 {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }

        List<Integer> list = new LinkedList<>();
        dfs(list, root);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        int pre = 0;
        for (int i = 0; i < list.size(); i++) {
            sum -= pre;
            pre = list.get(i);
            list.set(i, sum);
        }


        dfs2(list, root);
        return root;
    }

    private void dfs(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(list, node.left);
        list.add(node.val);
        dfs(list, node.right);
    }

    private void dfs2(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        dfs2(list, node.left);
        node.val = list.get(0);
        list.remove(0);
        dfs2(list, node.right);
    }
}
