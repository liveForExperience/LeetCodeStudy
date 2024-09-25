package com.bottomlord.week_009;

import java.util.*;

public class LeetCode_950_1_按递增顺序显示卡牌 {
    public int[] deckRevealedIncreasing(int[] deck) {
        int[] ans = new int[deck.length];
        Arrays.sort(deck);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < deck.length; i++) {
            list.add(i);
        }

        rescurse(ans, deck, list, true, 0);
        return ans;
    }

    private void rescurse(int[] ans, int[] deck, List<Integer> list, boolean take, int di) {
        if (list.isEmpty()) {
            return;
        }

        List<Integer> newList = new ArrayList<>();
        for (Integer num : list) {
            if (take) {
                ans[num] = deck[di++];
            } else {
                newList.add(num);
            }
            take = !take;
        }
        rescurse(ans, deck, newList, take, di);
    }
}
