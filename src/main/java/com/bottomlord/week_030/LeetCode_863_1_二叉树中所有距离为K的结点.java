package com.bottomlord.week_030;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/30 14:59
 */
public class LeetCode_863_1_二叉树中所有距离为K的结点 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        dfs(root, null, map);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(target);

        Set<TreeNode> set = new HashSet<>();
        set.add(target);
        int level = 0;

        while (!queue.isEmpty()) {
            if (level == K) {
                List<Integer> list = new ArrayList<>();
                while (!queue.isEmpty()) {
                    list.add(queue.poll().val);
                }
                return list;
            }

            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();

                if (node.left != null && !set.contains(node.left)) {
                    set.add(node.left);
                    queue.add(node.left);
                }

                if (node.right != null && !set.contains(node.right)) {
                    set.add(node.right);
                    queue.add(node.right);
                }

                if (map.get(node) != null && !set.contains(map.get(node))) {
                    set.add(map.get(node));
                    queue.add(map.get(node));
                }
            }

            level++;
        }

        return Collections.emptyList();
    }

    private void dfs(TreeNode node, TreeNode pre, Map<TreeNode, TreeNode> map) {
        if (node == null) {
            return;
        }

        map.put(node, pre);
        dfs(node.left, node, map);
        dfs(node.right, node, map);
    }
}
