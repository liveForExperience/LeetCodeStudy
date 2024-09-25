package com.bottomlord.week_092;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/4/13 8:33
 */
public class LeetCode_783_2 {
    public int minDiffInBST(TreeNode root) {
        Result result = new Result(-1, Integer.MAX_VALUE);
        dfs(root, result);
        return result.min;
    }

    private void dfs(TreeNode node, Result result) {
        if (node == null) {
            return;
        }

        dfs(node.left, result);
        if (result.init) {
            result.init = false;
        } else {
            result.min = Math.min(result.min, node.val - result.pre);
        }
        result.pre = node.val;
        dfs(node.right, result);
    }

    static class Result {
        private boolean init;
        private int pre;
        private int min;
        public Result(int pre, int min) {
            this.pre = pre;
            this.min = min;
            this.init = true;
        }
    }
}
