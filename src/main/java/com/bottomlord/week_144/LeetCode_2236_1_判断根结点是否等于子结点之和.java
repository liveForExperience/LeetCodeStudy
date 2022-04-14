package com.bottomlord.week_144;

import com.bottomlord.TreeNode;

/**
 * @author chen yue
 * @date 2022-04-14 17:21:33
 */
public class LeetCode_2236_1_判断根结点是否等于子结点之和 {
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}
