package com.bottomlord.week_142;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-03-31 20:21:46
 */
public class LeetCode_662_1_二叉树最大深度 {
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Object[]> queue = new ArrayDeque<>();
        queue.offer(new Object[]{root, 1});

        int ans = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            while (count-- > 0) {
                Object[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                TreeNode node = (TreeNode) arr[0];
                int num = (int) arr[1];

                min = Math.min(min, num);
                max = Math.max(max, num);

                if (node.left != null) {
                    queue.offer(new Object[]{node.left, num * 2 - 1});
                }

                if (node.right != null) {
                    queue.offer(new Object[]{node.right, num * 2});
                }
            }

            ans = Math.max(max - min + 1, ans);
        }

        return ans;
    }
}
