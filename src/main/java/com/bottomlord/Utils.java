package com.bottomlord;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-11-11 14:20:22
 */
public class Utils {
    private Utils(){}

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
