package com.bottomlord.week_134;

import com.bottomlord.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-01-31 22:17:44
 */
public class LeetCode_LCP44_1_开幕式烟火 {
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        return set.size();
    }

    private void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return;
        }

        set.add(node.val);
        dfs(node.left, set);
        dfs(node.right, set);
    }
}
