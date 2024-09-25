package com.bottomlord.week_094;

import java.util.List;

/**
 * @author ChenYue
 * @date 2021/4/26 10:16
 */
public class LeetCode_544_1_输出比赛匹配对 {
    public String findContestMatch(int n) {
        String[] arr = new String[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = "" + i;
        }

        return recuse(arr);
    }

    private String recuse(String[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        String[] newArr = new String[arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            newArr[i] = combineStr(arr[i], arr[arr.length - 1 - i]);
        }

        return recuse(newArr);
    }

    private String combineStr(String x, String y) {
        return "(" + x + "," + y + ")";
    }
}