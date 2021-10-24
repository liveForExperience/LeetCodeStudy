package com.bottomlord.week_119;

import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-24 18:38:26
 */
public class LeetCode_638_1_大礼包 {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = 0;

        for (int i = 0; i < price.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }

        for (int i = 0; i < special.size(); i++) {
            List<Integer> bag = special.get(i);

            boolean flag = false;
            for (int j = 0; j < bag.size() - 1; j++) {
                if (bag.get(j) > needs.get(j)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                continue;
            }

            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) - bag.get(j));
            }

            ans = Math.min(ans, shoppingOffers(price, special, needs) + bag.get(bag.size() - 1));

            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) + bag.get(j));
            }
        }

        return ans;
    }
}
