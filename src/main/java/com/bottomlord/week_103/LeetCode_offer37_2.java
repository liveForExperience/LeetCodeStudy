package com.bottomlord.week_103;

import com.bottomlord.TreeNode;

/**
 * @author ChenYue
 * @date 2021/6/30 8:54
 */
public class LeetCode_offer37_2 {
    public static class Codec {

        public String serialize(TreeNode root) {
            if (root == null) {
                return "X";
            }
            String left = "(" + serialize(root.left) + ")";
            String right = "(" + serialize(root.right) + ")";
            return left + root.val + right;
        }

        public TreeNode deserialize(String data) {
            return parse(data, new int[]{0});
        }

        private TreeNode parse(String data, int[] index) {
            if (data.charAt(index[0]) == 'X') {
                index[0]++;
                return null;
            }

            TreeNode node = new TreeNode(0);
            node.left = subParse(data, index);
            node.val = parseInt(data, index);
            node.right = subParse(data, index);
            return node;
        }

        private TreeNode subParse(String data, int[] index) {
            index[0]++;
            TreeNode node = parse(data, index);
            index[0]++;
            return node;
        }

        private int parseInt(String data, int[] index) {
            int digit = 0;
            boolean sign = true;
            if (data.charAt(index[0]) == '-') {
                index[0]++;
                sign = false;
            }

            while (Character.isDigit(data.charAt(index[0]))) {
                digit = digit * 10 + (data.charAt(index[0]++) - '0');
            }
            return sign ? digit : -digit;
        }
    }
}
