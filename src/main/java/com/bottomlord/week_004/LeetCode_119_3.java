package com.bottomlord.week_004;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/30 13:11
 */
public class LeetCode_119_3 {
    public List<Integer> getRow(int rowIndex) {
        return rescurse(new ArrayList<>(), 0, rowIndex);
    }

    private List<Integer> rescurse(List<Integer> preList, int row, int rowIndex) {
        if (row > rowIndex) {
            return preList;
        }

        List<Integer> curList = new ArrayList<>(row + 1);
        for (int i = 0; i <= row; i++) {
            if (i == 0 || i == row) {
                curList.add(1);
                continue;
            }

            curList.add(preList.get(i - 1) + preList.get(i));
        }

        return rescurse(curList, row + 1, rowIndex);
    }
}
