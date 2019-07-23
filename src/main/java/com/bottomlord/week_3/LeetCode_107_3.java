package com.bottomlord.week_3;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/23 18:08
 */
public class LeetCode_107_3 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        recurse(ans, 0, root);
        Collections.reverse(ans);
        return ans;
    }

    private void recurse(List<List<Integer>> ans, int level, TreeNode node) {
        if (node == null) {
            return;
        }

        if (level >= ans.size()) {
            List<Integer> list = new ArrayList<>();
            ans.add(list);
        }

        ans.get(level).add(node.val);

        recurse(ans, level + 1, node.left);
        recurse(ans, level + 1, node.right);
    }
}
