package com.bottomlord.week_8;

public class LeetCode_1128_3 {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] dict = new int[10][10];
        for (int[] dominoe : dominoes) {
            dict[dominoe[0]][dominoe[1]]++;
        }

        int sum = 0;
        for (int i = 0; i < dict.length; i++) {
            for (int j = i; j < dict.length; j++) {
                int num = dict[i][j];
                if (i != j) {
                    num += dict[j][i];
                }
                sum += num * (num - 1) / 2;
            }
        }

        return sum;
    }
}