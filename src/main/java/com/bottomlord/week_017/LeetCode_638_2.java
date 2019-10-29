package com.bottomlord.week_017;

import java.util.List;

public class LeetCode_638_2 {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = 0;

        for (int i = 0; i < price.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }

        for (int i = 0; i < special.size(); i++) {
            List<Integer> bag = special.get(i);
            boolean flag = true;
            for (int j = 0; j < bag.size() - 1; j++) {
                if (needs.get(j) < bag.get(j)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) {
                continue;
            }

            for (int j = 0; j < bag.size() - 1; j++) {
                needs.set(j, needs.get(j) - bag.get(j));
            }

            ans = Math.min(ans, shoppingOffers(price, special, needs) + bag.get(price.size()));

            for (int j = 0; j < bag.size() - 1; j++) {
                needs.set(j, needs.get(j) + bag.get(j));
            }
        }

        return ans;
    }
}