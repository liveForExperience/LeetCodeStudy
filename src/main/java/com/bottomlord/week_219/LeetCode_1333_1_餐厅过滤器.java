package com.bottomlord.week_219;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-09-27 15:31:03
 */
public class LeetCode_1333_1_餐厅过滤器 {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < restaurants.length; i++) {
            int[] restaurant = restaurants[i];
            if (!(restaurant[2] == 0 && veganFriendly == 1) && restaurant[3] <= maxPrice && restaurant[4] <= maxDistance) {
                list.add(i);
            }
        }

        list.sort((x, y) -> {
            int ratingX = restaurants[x][1], ratingY = restaurants[y][1];

            if (ratingX == ratingY) {
                return restaurants[y][0] - restaurants[x][0];
            }

            return ratingY - ratingX;
        });

        List<Integer> ans = new ArrayList<>();
        for (Integer i : list) {
            ans.add(restaurants[i][0]);
        }

        return ans;
    }
}
