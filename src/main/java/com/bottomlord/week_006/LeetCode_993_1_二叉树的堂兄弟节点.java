package com.bottomlord.week_006;

import com.bottomlord.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_993_1_二叉树的堂兄弟节点 {
    private Map<Integer, Integer> depthMap = new HashMap<>();
    private Map<Integer, TreeNode> parMap = new HashMap<>();

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null);
        return depthMap.get(x).equals(depthMap.get(y)) && parMap.get(x) != parMap.get(y);
    }

    private void dfs(TreeNode node, TreeNode par) {
        if (node == null) {
            return;
        }

        depthMap.put(node.val, par != null ? depthMap.get(par.val) + 1: 0);
        parMap.put(node.val, par);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}