package com.bottomlord.week_166;

/**
 * @author chen yue
 * @date 2022-09-14 07:36:57
 */
public class LeetCode_2383_1_赢得比赛需要的最少训练时长 {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int n = energy.length, time = 0;
        for (int i = 0; i < n; i++) {
            int e = energy[i], exp = experience[i];
            if (initialEnergy <= e) {
                int ec = e - initialEnergy + 1;
                time += ec;
                initialEnergy += ec;
            }

            if (initialExperience <= exp) {
                int expc = exp - initialExperience + 1;
                time += expc;
                initialExperience += expc;
            }

            initialEnergy -= e;
            initialExperience += exp;
        }

        return time;
    }
}
