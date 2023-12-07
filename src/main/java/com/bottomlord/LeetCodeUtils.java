package com.bottomlord;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/4/29 10:02
 */
public class LeetCodeUtils {
    private LeetCodeUtils() {}
    public static int[] convertArr(String str) {
        str = str.substring(1, str.length() - 1);
        String[] factors = str.split(",");
        return Arrays.stream(factors).map(Integer::parseInt).mapToInt(x -> x).toArray();
    }

    public static List<Integer> convertList(String str) {
        return Arrays.stream(convertArr(str)).boxed().collect(Collectors.toList());
    }

    public static int[][] convertMatrix(String str) {
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
