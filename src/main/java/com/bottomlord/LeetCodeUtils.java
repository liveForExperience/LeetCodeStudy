package com.bottomlord;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/4/29 10:02
 */
public class LeetCodeUtils {
    private LeetCodeUtils() {}

    public static TreeNode getTreeFromStr(String str) {
        Integer[] arr = getNumArrFromStr(str);
        return getTreeFromArr(arr, 0);
    }

    public static Integer[] getNumArrFromStr(String str) {
        String[] strArr = str.substring(1, str.length() - 1).split(",");
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String elementStr = strArr[i];
            arr[i] = Objects.equals("null", elementStr) ? null : Integer.parseInt(elementStr);
        }
        return arr;
    }

    public static void bfsPrintTree(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<String> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                list.add("null");
                continue;
            }

            list.add("" + node.val);
            queue.add(node.left);
            queue.add(node.right);
        }

        System.out.println(list);
    }

    private static TreeNode getTreeFromArr(Integer[] arr, int index) {
        if (index >= arr.length) {
            return null;
        }

        Integer val = arr[index];
        if (val == null) {
            return null;
        }

        TreeNode node = new TreeNode(val);
        node.left = getTreeFromArr(arr, (index + 1) * 2 - 1);
        node.right = getTreeFromArr(arr, (index + 1) * 2);

        return node;
    }
}
