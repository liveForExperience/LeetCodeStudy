package com.bottomlord.week_175;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-11-19 09:00:09
 */
public class LeetCode_792_1_匹配子序列的单词数 {
    public int numMatchingSubseq(String s, String[] words) {
        List[] lists = new ArrayList[26];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList();
        }

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            lists[c - 'a'].add(i);
        }

        int count = 0;
        for (String word : words) {
            int si = -1;
            boolean flag = true;
            char[] wcs = word.toCharArray();
            for (char c : wcs) {
                si = binarySearch(lists[c - 'a'], si);
                if (si == -1) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
            }
        }

        return count;
    }

    private static int binarySearch(List<Integer> list, int target) {
        int head = 0, tail = list.size();
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            int num = list.get(mid);
            if (num <= target) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        if (head >= list.size()) {
            return -1;
        }

        return list.get(head);
    }
}
