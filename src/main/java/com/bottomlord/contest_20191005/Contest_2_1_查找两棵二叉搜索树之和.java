package com.bottomlord.contest_20191005;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Contest_2_1_查找两棵二叉搜索树之和 {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        dfs(root2, list2);

        for (int num1 : list1) {
            for (int num2 : list2) {
                if (num1 + num2 == target) {
                    return true;
                }
            }
        }

        return false;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
