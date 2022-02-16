package com.bottomlord.week_136;

import com.bottomlord.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-02-16 10:38:44
 */
public class LeetCode_offerII56_1_二叉搜索树中两个节点之和 {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        for (Integer num : set) {
            if (num != k - num && set.contains(k - num)) {
                return true;
            }
        }
        return false;
    }

    private void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return;
        }

        dfs(node.left, set);
        set.add(node.val);
        dfs(node.right, set);
    }
}
