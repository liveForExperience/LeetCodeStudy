package com.bottomlord.week_034;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/26 8:40
 */
public class Interview_34_1_二叉树中和为某一值的路径 {
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
                list.add(node.val);
                ans.add(new ArrayList<>(list));
                list.removeLast();
                return;
            }
        }

        list.add(node.val);
        backTrack(node.left, count, sum, list, ans);
        list.removeLast();

        list.add(node.val);
        backTrack(node.right, count, sum, list, ans);
        list.removeLast();
    }
}
