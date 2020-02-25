package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/2/25 9:01
 */
public class Interview_33_1_二叉搜索树后序遍历序列 {
    public boolean verifyPostorder(int[] postorder) {
        return recurse(postorder, 0, postorder.length - 1);
    }

    private boolean recurse(int[] postOrder, int start, int end) {
        if (end - start < 2) {
            return true;
        }

        int root = postOrder[end], right = start;

        for (; right < end; right++) {
            if (postOrder[right] > root) {
                break;
            }
        }

        for (int i = right; i < end; i++) {
            if (postOrder[i] < root) {
                return false;
            }
        }

        return recurse(postOrder, start, right - 1) && recurse(postOrder, right, end - 1);
    }
}
