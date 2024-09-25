package com.bottomlord.week_017;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeetCode_337_1_打家劫舍III {
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size(), sum = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            list.add(sum);
        }

        if (list.size() == 0) {
            return 0;
        }

        if (list.size() == 1) {
            return list.get(0);
        }

        if (list.size() == 2) {
            return Math.max(list.get(0), list.get(1));
        }

        int[] dp = new int[list.size()];
        dp[0] = list.get(0);
        dp[1] = Math.max(list.get(0), list.get(1));

        for (int i = 2; i < list.size(); i++) {
            dp[i] = Math.max(dp[i - 2] + list.get(i), dp[i - 1]);
        }

        return dp[list.size() - 1];
    }
}
