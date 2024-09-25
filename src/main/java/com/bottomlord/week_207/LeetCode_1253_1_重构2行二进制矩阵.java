package com.bottomlord.week_207;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-06-29 20:48:12
 */
public class LeetCode_1253_1_重构2行二进制矩阵 {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> one = new ArrayList<>(), two = new ArrayList<>();
        for (int num : colsum) {
            if (num == 2) {
                one.add(1);
                two.add(1);
                upper--;
                lower--;
            } else if (num == 0) {
                one.add(0);
                two.add(0);
            } else if (upper >= lower) {
                one.add(1);
                two.add(0);
                upper--;
            } else {
                one.add(0);
                two.add(1);
                lower--;
            }

            if (upper < 0 || lower < 0) {
                return ans;
            }
        }

        if (upper != 0 || lower != 0) {
            return ans;
        }
        ans.add(one);
        ans.add(two);
        return ans;
    }
}
