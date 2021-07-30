package com.bottomlord.week_107;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/7/29 8:46
 */
public class LeetCode_1104_2 {
    public List<Integer> pathInZigZagTree(int label) {
        int row = 1, rowStart = 1;
        while (rowStart <= label) {
            rowStart <<= 1;
            row++;
        }

        row--;
        LinkedList<Integer> ans = new LinkedList<>();
        while (row >= 1) {
            boolean even = row % 2 == 0;
            ans.addFirst(label);
            if (row == 1) {
                break;
            }

            int sum = (int) (Math.pow(2, row - 1) + Math.pow(2, row) - 1);
            if (even) {
                label = (sum - label) / 2;
            } else {
                int pre = label / 2;
                label = (int) (Math.pow(2, row - 2) + Math.pow(2, row - 1) - 1) - pre;
            }

            row--;
        }

        return ans;
    }
}
