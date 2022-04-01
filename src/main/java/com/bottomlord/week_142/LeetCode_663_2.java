package com.bottomlord.week_142;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-04-01 21:46:30
 */
public class LeetCode_663_2 {
    public boolean checkEqualTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        int sum = dfs(root, list);

        for (int num : list) {
            if (sum - num == num) {
                return true;
            }
        }

        return false;
    }

    private int dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        int left = dfs(node.left, list),
                right = dfs(node.right, list);

        if(left != Integer.MAX_VALUE) {
            list.add(left);
        }

        if (right != Integer.MAX_VALUE) {
            list.add(right);
        }

        return (left == Integer.MAX_VALUE ? 0 : left) +
                (right == Integer.MAX_VALUE ? 0 : right) +
                node.val;
    }
}
