package com.bottomlord.week_141;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-21 09:09:37
 */
public class LeetCode_653_1_两数之和IV_输入BST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int sum = list.get(head) + list.get(tail);
            if (sum > k) {
                tail--;
            } else if (sum < k) {
                head++;
            } else {
                return true;
            }
        }

        return false;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
