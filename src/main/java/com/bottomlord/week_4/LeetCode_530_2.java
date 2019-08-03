package com.bottomlord.week_4;

import com.bottomlord.TreeNode;

/**
 * @author LiveForExperience
 * @date 2019/8/3 16:19
 */
public class LeetCode_530_2 {
    private Integer pre = null;
    private Integer min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);

        if (pre != null) {
            min = Math.min(min, Math.abs(node.val - pre));
        }

        pre = node.val;
        dfs(node.right);
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(0);
        TreeNode node2236 = new TreeNode(2236);
        TreeNode node1277 = new TreeNode(1277);
        TreeNode node2276 = new TreeNode(2776);
        TreeNode node519 = new TreeNode(519);

        node0.right = node2236;
        node2236.left = node1277;
        node2236.right = node2276;
        node1277.left = node519;

        LeetCode_530_2 test = new LeetCode_530_2();
        test.getMinimumDifference(node0);
    }
}
