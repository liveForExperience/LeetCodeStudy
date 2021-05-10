package com.bottomlord.week_096;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ChenYue
 * @date 2021/5/10 9:37
 */
public class LeetCode_872_1_叶子相似的树 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
        }

        dfs(node.left, list);
        dfs(node.right, list);
    }
}
