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
        if (arr == null || arr.length == 0) {
            return null;
        }

        return dfs(arr, 0);
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

    private static TreeNode dfs(Integer[] arr, int index) {
        if (index >= arr.length) {
            return null;
        }

        if (arr[index] == null) {
            return null;
        }

        TreeNode node = new TreeNode(arr[index]);
        node.left = dfs(arr, index * 2 + 1);
        node.right = dfs(arr, index * 2 + 2);
        return node;
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
