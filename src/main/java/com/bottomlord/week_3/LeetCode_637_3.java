package com.bottomlord.week_3;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author LiveForExperience
 * @date 2019/7/26 10:33
 */
public class LeetCode_637_3 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        List<Integer> levelNum = new ArrayList<>();
        List<Double> levelSum = new ArrayList<>();
        Stack<int[]> entryStack = new Stack<>();
        int[] rootEntry = new int[]{root.val, 0};
        entryStack.push(rootEntry);

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(root);

        while (!entryStack.isEmpty()) {
            int[] entry = entryStack.pop();
            int val = entry[0];
            int level = entry[1];

            if (level >= levelNum.size()) {
                levelNum.add(1);
                levelSum.add((double) val);
            } else {
                levelNum.set(level, levelNum.get(level) + 1);
                levelSum.set(level, levelSum.get(level) + val);
            }

            TreeNode node = nodeStack.pop();
            if (node.left != null) {
                int[] leftEntry = new int[]{node.left.val, level + 1};
                entryStack.push(leftEntry);
                nodeStack.push(node.left);
            }

            if (node.right != null) {
                int[] rightEntry = new int[]{node.right.val, level + 1};
                entryStack.push(rightEntry);
                nodeStack.push(node.right);
            }
        }

        for (int i = 0; i < levelNum.size(); i++) {
            ans.add(levelSum.get(i) / levelNum.get(i));
        }

        return ans;
    }
}