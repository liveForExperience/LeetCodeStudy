package com.bottomlord.week_128;

import com.bottomlord.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2021-12-25 14:48:13
 */
public class LeetCode_1609_1_奇偶树 {
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int count = queue.size();
            Integer pre = null;
            while (count-- > 0) {
                TreeNode node = queue.poll();
                int val = node.val;
                if (level % 2 == 0) {
                    if (val % 2 != 1) {
                        return false;
                    }

                    if (pre != null) {
                        if (val <= pre) {
                            return false;
                        }
                    }
                } else {
                    if (val % 2 != 0) {
                        return false;
                    }

                    if (pre != null) {
                        if (val >= pre) {
                            return false;
                        }
                    }
                }
                pre = val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            level++;
        }

        return true;
    }
}
