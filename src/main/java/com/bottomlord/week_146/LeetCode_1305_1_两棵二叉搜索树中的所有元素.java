package com.bottomlord.week_146;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-05-01 00:36:10
 */
public class LeetCode_1305_1_两棵二叉搜索树中的所有元素 {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(),
                      list2 = new ArrayList<>();

        dfs(root1, list1);
        dfs(root2, list2);

        List<Integer> ans = new ArrayList<>();
        int i1 = 0, i2 = 0;
        while (i1 < list1.size() || i2 < list2.size()) {
            if (i1 >= list1.size()) {
                ans.add(list2.get(i2++));
            } else if (i2 >= list2.size()) {
                ans.add(list1.get(i1++));
            } else {
                int val1 = list1.get(i1), val2 = list2.get(i2);
                if (val1 < val2) {
                    ans.add(val1);
                    i1++;
                } else {
                    ans.add(val2);
                    i2++;
                }
            }
        }

        return ans;
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
