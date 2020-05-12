package com.bottomlord.week_045;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/5/12 8:19
 */
public class Interview_1717_2 {
    public int[][] multiSearch(String big, String[] smalls) {
        TrieTree tree = new TrieTree(smalls);

        for (int i = 0; i < smalls.length; i++) {
            tree.insert(smalls[i], i);
        }

        int len = big.length();
        for (int i = 0; i < len; i++) {
            tree.update(big.substring(i, len), i);
        }

        int[][] ans = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            ans[i] = tree.ans[i].stream().mapToInt(x -> x).toArray();
        }
        return ans;
    }
}