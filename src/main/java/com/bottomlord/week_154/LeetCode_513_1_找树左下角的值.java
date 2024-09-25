package com.bottomlord.week_154;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-06-22 22:21:42
 */
public class LeetCode_513_1_找树左下角的值 {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int ans = -1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean start = true;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                if (start) {
                    ans = node.val;
                    start = false;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return ans;
    }
}
