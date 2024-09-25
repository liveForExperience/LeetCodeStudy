package com.bottomlord.week_244;

import com.bottomlord.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2024-03-12 10:08:40
 */
public class LeetCode_1261_1_在受污染的二叉树中查找元素 {
    class FindElements {

        private final Set<Integer> set;

        public FindElements(TreeNode root) {
            this.set = new HashSet<>();
            root.val = 0;
            init(root);
        }

        public boolean find(int target) {
            return this.set.contains(target);
        }

        private void init(TreeNode node) {
            if (node == null) {
                return;
            }

            int val = node.val;
            this.set.add(val);

            if (node.left != null) {
                node.left.val = val * 2 + 1;
                init(node.left);
            }

            if (node.right != null) {
                node.right.val = val * 2 + 2;
                init(node.right);
            }
        }
    }
}
