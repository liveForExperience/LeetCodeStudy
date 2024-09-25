package com.bottomlord.week_239;

import com.bottomlord.TreeNode;

import java.util.*;

/**
 * @author chen yue
 * @date 2024-02-07 15:55:38
 */
public class LeetCode_2641_1_二叉树的堂兄弟节点II {
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode[]> queue = new ArrayDeque<>();
        queue.offer(new TreeNode[]{root, root});
        while (!queue.isEmpty()) {
            int count = queue.size(), sum = 0;
            List<TreeNode[]> list = new ArrayList<>();
            Map<TreeNode, Integer> map = new HashMap<>();
            while (count-- > 0) {
                TreeNode[] item = queue.poll();

                if (item == null) {
                    continue;
                }

                TreeNode parentItem = item[0], curItem = item[1];

                list.add(item);
                int curValue = curItem.val;
                map.put(parentItem, map.getOrDefault(parentItem, 0) + curValue);
                sum += curValue;

                if (curItem.left != null) {
                    queue.offer(new TreeNode[]{curItem, curItem.left});
                }

                if (curItem.right != null) {
                    queue.offer(new TreeNode[]{curItem, curItem.right});
                }
            }

            for (TreeNode[] item : list) {
                item[1].val = sum - map.get(item[0]);
            }
        }

        return root;
    }
}
