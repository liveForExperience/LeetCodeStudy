package com.bottomlord.week_003;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/25 22:22
 */
public class LeetCode_637_2 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        List<Integer> levelNum = new ArrayList<>();
        List<Double> levelSum = new ArrayList<>();

        rescurse(root, 0, levelNum, levelSum);

        for (int i = 0; i < levelNum.size(); i++) {
            ans.add(levelSum.get(i) / levelNum.get(i));
        }

        return ans;
    }

    private void rescurse(TreeNode node, int level, List<Integer> levelNum, List<Double> levelSum) {
        if (node == null) {
            return;
        }

        if (levelNum.size() <= level) {
            levelNum.add(1);
            levelSum.add((double) node.val);
        } else {
            levelNum.set(level, levelNum.get(level) + 1);
            levelSum.set(level, levelSum.get(level) + node.val);
        }

        rescurse(node.left, level + 1, levelNum, levelSum);
        rescurse(node.right, level + 1, levelNum, levelSum);
    }
}