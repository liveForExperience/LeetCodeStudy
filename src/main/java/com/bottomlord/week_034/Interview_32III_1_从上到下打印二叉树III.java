package com.bottomlord.week_034;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/2/25 8:44
 */
public class Interview_32III_1_从上到下打印二叉树III {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        boolean right = false;
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>(count);
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }

                list.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (right) {
                Collections.reverse(list);
            }
            ans.add(list);
            right = !right;
        }

        return ans;
    }
}
