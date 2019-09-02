package com.bottomlord.week_002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/21 17:06
 */
public class LeetCode_118_1_杨辉三角 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>(i + 1);
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                    continue;
                }

                List<Integer> preList = ans.get(i - 1);
                list.add(preList.get(j - 1) + preList.get(j));
            }
            ans.add(list);
        }

        return ans;
    }
}
