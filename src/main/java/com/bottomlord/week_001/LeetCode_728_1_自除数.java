package com.bottomlord.week_001;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/12 13:23
 */
public class LeetCode_728_1_自除数 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            String str = Integer.toString(i);
            String[] strs = str.split("");
            for (String s: strs) {
                if ("0".equals(s)) {
                    set.clear();
                    break;
                }
                set.add(s);
            }

            if (set.size() == 0) {
                continue;
            }

            boolean flag = true;
            for (String s: set) {
                if (i % Integer.parseInt(s) != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans.add(i);
            }
            set.clear();
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_728_1_自除数 a = new LeetCode_728_1_自除数();
        System.out.println(a.selfDividingNumbers(3, 4));
    }
}
