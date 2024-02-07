package com.bottomlord.week_239;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-02-07 16:38:17
 */
public class LeetCode_2641_2 {
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        int sum = root.val;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size(), nextSum = 0;
            while (count-- > 0) {
                TreeNode cur = queue.poll();

                if (cur == null) {
                    continue;
                }

                cur.val = sum - cur.val;
                TreeNode left = cur.left, right = cur.right;
                int lv = left == null ? 0 : left.val, rv = right == null ? 0 : right.val;

                if (left != null) {
                    nextSum += left.val;
                    queue.offer(left);
                    left.val = lv + rv;
                }

                if (cur.right != null) {
                    nextSum += right.val;
                    queue.offer(right);
                    right.val = lv + rv;
                }
            }

            sum = nextSum;
        }

        return root;
    }
}