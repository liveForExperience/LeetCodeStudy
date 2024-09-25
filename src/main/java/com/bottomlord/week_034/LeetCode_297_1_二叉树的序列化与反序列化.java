package com.bottomlord.week_034;

import com.bottomlord.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author ThinkPad
 * @date 2020/2/27 9:11
 */
public class LeetCode_297_1_二叉树的序列化与反序列化 {
    public class Codec {
        public String serialize(TreeNode root) {
            return root == null ? "null" : String.valueOf(root.val) + "," +  serialize(root.left) + "," + serialize(root.right);
        }

        public TreeNode deserialize(String data) {
            return doDeserialize(new LinkedList<>(Arrays.asList(data.split(","))));
        }

        private TreeNode doDeserialize(LinkedList<String> list) {
            String num = list.removeFirst();

            if (Objects.equals(num, "null")) {
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(num));
            node.left = doDeserialize(list);
            node.right = doDeserialize(list);

            return node;
        }
    }
}
