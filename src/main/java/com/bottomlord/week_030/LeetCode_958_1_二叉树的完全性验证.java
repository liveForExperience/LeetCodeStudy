package com.bottomlord.week_030;

import com.bottomlord.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/30 14:00
 */
public class LeetCode_958_1_二叉树的完全性验证 {
    public boolean isCompleteTree(TreeNode root) {
        List<Anode> list = new ArrayList<>();
        list.add(new Anode(root, 1));
        int i = 0;
        while (i < list.size()) {
            Anode anode = list.get(i++);

            if (anode.node != null) {
                list.add(new Anode(anode.node.left, anode.val * 2));
                list.add(new Anode(anode.node.right, anode.val * 2 + 1));
            }
        }

        return list.get(i - 1).val == list.size();
    }
}
