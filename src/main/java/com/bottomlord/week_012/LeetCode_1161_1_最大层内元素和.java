package com.bottomlord.week_012;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode_1161_1_最大层内元素和 {
    public int maxLevelSum(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(list, root, 1);

        int max = Collections.max(list);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == max) {
                return i + 1;
            }
        }

        return 0;
    }

    private void dfs(List<Integer> list, TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (list.size() < level) {
            list.add(node.val);
        } else {
            list.set(level - 1, list.get(level - 1) + node.val);
        }
        dfs(list, node.left, level + 1);
        dfs(list, node.right, level + 1);
    }
}
