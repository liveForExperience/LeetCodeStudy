package com.bottomlord.week_003;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/25 16:38
 */
public class LeetCode_637_1_二叉树的层平均值 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            int num = count;
            double total = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();

                if (node == null) {
                    continue;
                }

                total += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add(total / num);
        }

        return ans;
    }
}
