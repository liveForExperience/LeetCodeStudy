package com.bottomlord.week_5;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/8/5 12:59
 */
public class LeetCode_653_1_两数之和IV_输入BST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int ans = list.get(i) + list.get(j);

                if (ans == k) {
                    return true;
                }

                if (ans > k) {
                    break;
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
