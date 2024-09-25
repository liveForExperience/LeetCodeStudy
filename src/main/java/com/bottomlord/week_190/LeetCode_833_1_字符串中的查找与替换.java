package com.bottomlord.week_190;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2023-03-01 09:57:42
 */
public class LeetCode_833_1_字符串中的查找与替换 {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int n = s.length();
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i];
            if (index == -1) {
                sb.append(s.charAt(i));
            } else {
                if (s.startsWith(sources[index], indices[index])) {
                    sb.append(targets[index]);
                    i += sources[index].length() - 1;
                } else {
                    sb.append(s.charAt(i));
                }
            }
        }

        return sb.toString();
    }
}
