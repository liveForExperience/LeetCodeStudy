package com.bottomlord.week_159;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-08-01 08:41:13
 */
public class LeetCode_1161_1_最大层内元素和 {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int max = Integer.MIN_VALUE, index = 1, ans = index;

        while (!queue.isEmpty()) {
            int count = queue.size();

            int sum = 0;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (sum > max) {
                ans = index;
                max = sum;
            }

            index++;
        }

        return ans;
    }
}
