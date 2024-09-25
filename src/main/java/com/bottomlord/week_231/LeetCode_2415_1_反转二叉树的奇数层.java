package com.bottomlord.week_231;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-12-15 09:53:21
 */
public class LeetCode_2415_1_反转二叉树的奇数层 {
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode[]> queue = new ArrayDeque<>();
        boolean flag = false;
        queue.offer(new TreeNode[]{null, root});
        while (!queue.isEmpty()) {
            int count = queue.size();

            List<TreeNode> parents = new ArrayList<>(),
                           nodes = new ArrayList<>(),
                           children = new ArrayList<>();
            while (count-- > 0) {
                TreeNode[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                parents.add(arr[0]);
                TreeNode node = arr[1];
                nodes.add(node);

                if (node.left != null) {
                    children.add(node.left);
                    queue.offer(new TreeNode[]{node, node.left});
                }

                if (node.right != null) {
                    children.add(node.right);
                    queue.offer(new TreeNode[]{node, node.right});
                }
            }

            if (flag) {
                Collections.reverse(nodes);
                for (int i = 0; i < nodes.size(); i++) {
                    TreeNode node = nodes.get(i);
                    if (i % 2 == 0) {
                        parents.get(i).left = node;
                    } else {
                        parents.get(i).right = node;
                    }

                    if (children.isEmpty()) {
                        continue;
                    }

                    node.left = children.get(i * 2);
                    node.right = children.get(i * 2 + 1);
                }
            }

            flag = !flag;
        }

        return root;
    }
}
