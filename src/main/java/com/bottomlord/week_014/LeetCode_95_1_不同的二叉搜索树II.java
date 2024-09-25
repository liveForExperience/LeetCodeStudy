package com.bottomlord.week_014;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_95_1_不同的二叉搜索树II {
    public List<TreeNode> generateTrees(int n) {
        return doGenerate(1, n);
    }

    private List<TreeNode> doGenerate(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start >= end) {
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = doGenerate(start, i);
            List<TreeNode> rights = doGenerate(i + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }

        return list;
    }
}
