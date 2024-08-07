package com.bottomlord;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/4/29 10:02
 */
public class LeetCodeUtils {
    private LeetCodeUtils() {}

    public static TreeNode convertToTree(String str) {
        Integer[] arr = convertToIntegerArr(str);
        if (arr.length == 0 || arr[0] == null) {
            return null;
        }

        List<TreeNode> list = new ArrayList<>();
        TreeNode root = new TreeNode(arr[0]);
        list.add(root);
        recursive(arr, 1, list);
        return root;
    }

    public static Integer[] convertToIntegerArr(String str) {
        str = str.substring(1, str.length() - 1);
        String[] factors = str.split(",");
        Integer[] arr = new Integer[factors.length];
        for (int i = 0; i < factors.length; i++) {
            String factor = factors[i];
            if (factor == null) {
                arr[i] = null;
                continue;
            }

            if ("null".compareToIgnoreCase(factor) == 0) {
                arr[i] = null;
                continue;
            }

            arr[i] = Integer.parseInt(factor);
        }
        return arr;
    }

    public static int[] convertToIntArr(String str) {
        str = str.substring(1, str.length() - 1);
        String[] factors = str.split(",");
        return Arrays.stream(factors).map(Integer::parseInt).mapToInt(x -> x).toArray();
    }

    public static List<Integer> convertToIntList(String str) {
        return Arrays.stream(convertToIntArr(str)).boxed().collect(Collectors.toList());
    }

    public static String[] convertToStrArr(String str) {
        str = str.substring(1, str.length() - 1);
        String[] words = str.split(",");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(1, words[i].length() - 1);
        }
        return words;
    }

    public static List<String> convertToStrList(String str) {
        return Arrays.stream(convertToStrArr(str)).collect(Collectors.toList());
    }

    public static int[][] convertToMatrix(String str) {
        if (str == null || str.isEmpty()) {
            return new int[0][0];
        }

        str = str.replaceAll("[\n| ]", "");
        str = str.substring(1, str.length() - 1);
        List<List<Integer>> lists = getLists(str);

        if (lists.isEmpty()) {
            return new int[0][0];
        }

        int m = lists.size(), n = lists.get(0).size();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);
            for (int j = 0; j < list.size(); j++) {
                matrix[i][j] = list.get(j);
            }
        }

        return matrix;
    }

    private static void recursive(Integer[] arr, int si, List<TreeNode> preList) {
        if (si >= arr.length) {
            return;
        }

        List<TreeNode> nextPreList = new ArrayList<>();
        for (TreeNode treeNode : preList) {
            if (si < arr.length && arr[si] != null) {
                treeNode.left = new TreeNode(arr[si]);
                nextPreList.add(treeNode.left);
            }
            si++;

            if (si < arr.length && arr[si] != null) {
                treeNode.right = new TreeNode(arr[si]);
                nextPreList.add(treeNode.right);
            }
            si++;
        }

        recursive(arr, si, nextPreList);
    }

    private static List<List<Integer>> getLists(String str) {
        char[] cs = str.toCharArray();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < cs.length; i++) {
            List<Integer> list = new ArrayList<>();
            int cur = 0;
            while (cs[i] != ']') {
                char c = cs[i];
                if (c == ',') {
                    list.add(cur);
                    cur = 0;
                } else if (c != '[') {
                    cur = cur * 10 + (c - '0');
                }

                i++;
            }

            list.add(cur);
            i++;

            lists.add(list);
        }
        return lists;
    }
}
