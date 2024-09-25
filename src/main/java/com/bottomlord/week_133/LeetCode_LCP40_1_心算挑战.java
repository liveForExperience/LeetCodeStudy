package com.bottomlord.week_133;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-30 15:32:16
 */
public class LeetCode_LCP40_1_心算挑战 {
    private int sum = 0;
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int maxOddIndex = -1, maxEvenIndex = -1;
        for (int i = cards.length - 1; i >= 0; i--) {
            if (cards[i] % 2 == 0 && maxEvenIndex == -1) {
                maxEvenIndex = i;
            }

            if (cards[i] % 2 != 0 && maxOddIndex == -1) {
                maxOddIndex = i;
            }

            if (maxEvenIndex != -1 && maxOddIndex != -1) {
                break;
            }
        }

        if (maxEvenIndex + 1 >= cnt) {
            backTrack(cards, maxEvenIndex, 0, cnt, 0);
        }

        if (maxOddIndex + 1 >= cnt) {
            backTrack(cards, maxOddIndex, 0, cnt, 0);
        }

        return sum;
    }

    private boolean backTrack(int[] cards, int index, int time, int cnt, int sum) {
        if (time == cnt) {
            if (sum % 2 == 0) {
                this.sum = Math.max(this.sum, sum);
                return true;
            } else {
                return false;
            }
        }

        if (index < 0) {
            return false;
        }

        for (int i = index; i >= 0; i--) {
            boolean result = backTrack(cards, i - 1, time + 1, cnt, sum + cards[i]);
            if (result) {
                return true;
            }
        }

        return false;
    }
}
