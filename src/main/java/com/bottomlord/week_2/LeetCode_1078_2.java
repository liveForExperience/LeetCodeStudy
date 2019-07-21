package com.bottomlord.week_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/21 21:42
 */
public class LeetCode_1078_2 {
    public String[] findOcurrences(String text, String first, String second) {
        String[] strs = text.split(" ");
        boolean findFirst = false;
        boolean findSecod = false;
        List<String> ans = new ArrayList<>();

        for (String str: strs) {
            if (!findFirst && first.equals(str)) {
                findFirst = true;
                continue;
            }

            if (findFirst) {
                if (findSecod) {
                    ans.add(str);
                    findSecod = false;
                    if (!first.equals(str)) {
                        findFirst = false;
                    }
                    continue;
                }
                if (!second.equals(str) && !first.equals(str)) {
                    findFirst = false;
                    continue;
                }
                findSecod = true;
            }
        }

        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
        LeetCode_1078_2 test = new LeetCode_1078_2();
        System.out.println(Arrays.toString(test.findOcurrences("jkypmsxd jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa jkypmsxd kcyxdfnoa kcyxdfnoa jkypmsxd kcyxdfnoa"
                ,"kcyxdfnoa"
                ,"jkypmsxd")));
    }
}