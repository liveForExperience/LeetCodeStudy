package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/26 13:44
 */
public class LeetCode_1103_1_分糖果II {
    public int[] distributeCandies(int candies, int num_people) {
        int[] ans = new int[num_people];
        int num = 1;
        int count = 0;
        while (candies > 0) {
            if (candies < num) {
                num = candies;
            }

            candies -= num;

            ans[count % num_people] += num;

            num++;
            count++;
        }
        return ans;
    }
}
