package com.bottomlord.week_003;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author LiveForExperience
 * @date 2019/7/23 18:03
 */
public class LeetCode_107_2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

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

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
            }

            ans.add(list);
        }

        Collections.reverse(ans);
        return ans;
    }
}
