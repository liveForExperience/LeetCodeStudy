package com.bottomlord.week_143;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-04-05 17:30:58
 */
public class LeetCode_666_1_路径总和IV {
    private int ans = 0;

    public int pathSum(int[] nums) {
        int maxDepth = nums[nums.length - 1] / 100;
        List<TreeNode[]> list = new ArrayList<>();
        for (int i = 0; i < maxDepth; i++) {
            list.add(new TreeNode[1 << i]);
        }

        for (int num : nums) {
            int depth = num / 100, pos = (num % 100) / 10, val = num % 10;
            TreeNode node = new TreeNode(val);
            list.get(depth - 1)[pos - 1] = node;
            if (depth - 1 > 0) {
                if (pos % 2 == 1) {
                    list.get(depth - 2)[(pos - 1) / 2].left = node;
                } else {
                    list.get(depth - 2)[(pos - 1) / 2].right = node;
                }
            }
        }

        dfs(list.get(0)[0], 0);
        return ans;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            ans += node.val + sum;
        }

        dfs(node.left, sum + node.val);
        dfs(node.right, sum + node.val);
    }

    private class TreeNode {
        private int val;
        private TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
