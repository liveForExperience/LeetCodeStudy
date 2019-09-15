package com.bottomlord.week_010;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeetCode_173_1_二叉搜索树迭代器 {
    class BSTIterator {
        Iterator<Integer> iterator;

        public BSTIterator(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            dfs(list, root);
            iterator = list.iterator();
        }

        private void dfs(List<Integer> list, TreeNode node) {
            if (node == null) {
                return;
            }

            dfs(list, node.left);
            list.add(node.val);
            dfs(list, node.right);
        }

        public int next() {
            return iterator.next();
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }
    }
}
