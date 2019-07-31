package com.bottomlord.week_4;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/7/31 13:49
 */
public class LeetCode_538_把二叉搜索树转换成累加树 {
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            dfs(root, 0);
        }
        return root;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) {
            return sum;
        }

        sum = dfs(node.right, sum);

        int nodeValue = node.val;
        node.val += sum;
        sum += nodeValue;

        sum = dfs(node.left, sum);

        return sum;
    }
}
