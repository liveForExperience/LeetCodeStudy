package com.bottomlord.week_109;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-15 21:24:11
 */
public class LeetCode_1469_1_寻找所有独生节点 {
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list, false);
        return list;
    }

    private void dfs(TreeNode node, List<Integer> list, boolean single) {
        if (node == null) {
            return;
        }

        if (single) {
            list.add(node.val);
        }

        single = node.left == null || node.right == null;

        dfs(node.left, list, single);
        dfs(node.right, list, single);
    }
}
