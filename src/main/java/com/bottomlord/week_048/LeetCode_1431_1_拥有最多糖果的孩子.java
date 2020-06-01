package com.bottomlord.week_048;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/6/1 8:24
 */
public class LeetCode_1431_1_拥有最多糖果的孩子 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        List<Boolean> ans = new ArrayList<>();
        for (int candy : candies) {
            max = Math.max(candy, max);
        }

        for (int candy : candies) {
            ans.add(candy + extraCandies >= max);
        }

        return ans;
    }
}
