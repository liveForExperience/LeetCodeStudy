package com.bottomlord.week_017;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_638_1_大礼包 {
    private int min = Integer.MAX_VALUE;
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        for (int i = 0; i < price.size(); i++) {
            List<Integer> list = new ArrayList<>(price.size() + 1);
            for (int j = 0; j < price.size() + 1; j++) {
                if (i == j) {
                    list.add(1);
                    continue;
                }

                if (j == price.size()) {
                    list.add(price.get(i));
                    continue;
                }

                list.add(0);
            }
            special.add(list);
        }

        List<Integer> list = new ArrayList<>(needs.size());
        for (int i = 0; i < needs.size(); i++) {
            list.add(0);
        }
        backTrack(special, needs, list, 0);
        return min;
    }

    private void backTrack(List<List<Integer>> special, List<Integer> needs, List<Integer> cur, int sum) {
        boolean flag = true;
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i) > needs.get(i)) {
                return;
            }

            if (cur.get(i) < needs.get(i)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            min = Math.min(sum, min);
            return;
        }

        for (int i = 0; i < special.size(); i++) {
            List<Integer> bag = special.get(i);
            for (int j = 0; j < bag.size() - 1; j++) {
                cur.set(j, cur.get(j) + bag.get(j));
            }
            backTrack(special, needs, cur, sum + bag.get(bag.size() - 1));
            for (int j = 0; j < bag.size() - 1; j++) {
                cur.set(j, cur.get(j) - bag.get(j));
            }
        }
    }
}
