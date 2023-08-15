package com.bottomlord.week_214;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-08-15 14:00:29
 */
public class LeetCode_883_1_字符串中的查找和替换 {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = i;
        }

        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                sb.append(cs[i]);
            } else {
                int index = arr[i];
                if (s.startsWith(sources[index], i)) {
                    sb.append(targets[index]);
                    i += sources[index].length();
                } else {
                    sb.append(cs[i]);
                }
            }
        }

        return sb.toString();
    }
}
