package com.bottomlord.week_008;

public class LeetCode_1128_1_等价多米诺骨牌对的数量 {
    public int numEquivDominoPairs(int[][] dominoes) {
        int sum = 0;
        for (int i = 0; i < dominoes.length; i++) {
            for (int j = i + 1; j < dominoes.length; j++) {
                int[] left = dominoes[i];
                int[] right = dominoes[j];

                if ((left[0] == right[0]) && (right[1] == left[1]) || (left[0] == right[1] && left[1] == right[0])) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
