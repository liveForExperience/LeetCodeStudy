package com.bottomlord.week_015;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_113_1_路径总和II {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(root, 0, sum, new LinkedList<>(), ans);
        return ans;
    }

    private void backTrack(TreeNode node, int count, int sum, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }

        count += node.val;

        if (node.left == null && node.right == null) {
            if (count == sum) {
                list.addLast(node.val);
                ans.add(new LinkedList<>(list));
            }
            return;
        }

        list.addLast(node.val);
        backTrack(node.left, count, sum, list, ans);
        list.removeLast();

        list.addLast(node.val);
        backTrack(node.right, count, sum, list, ans);
        list.removeLast();
    }
}
