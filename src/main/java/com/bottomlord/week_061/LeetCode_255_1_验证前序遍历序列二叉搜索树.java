package com.bottomlord.week_061;

/**
 * @author ChenYue
 * @date 2020/9/6 16:08
 */
public class LeetCode_255_1_验证前序遍历序列二叉搜索树 {
    public boolean verifyPreorder(int[] preorder) {
        return recurse(preorder, 0, preorder.length - 1);
    }

    private boolean recurse(int[] preorder, int start, int end) {
        if (start >= end) {
            return true;
        }

        int i = end;
        while (i >= start && preorder[i] > preorder[start]) {
            i--;
        }

        for (int j = start; j <= i; j++) {
            if (preorder[j] > preorder[start]) {
                return false;
            }
        }

        return recurse(preorder, start + 1, i) && recurse(preorder, i + 1, end);
    }
}
