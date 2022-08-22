package com.bottomlord.week_162;

/**
 * @author chen yue
 * @date 2022-08-21 17:07:56
 */
public class LeetCode_2347_1_最好的扑克手牌 {
    public String bestHand(int[] ranks, char[] suits) {
        int[] suitBucket = new int[4], rankBucket = new int[10];
        for (int rank : ranks) {
            rankBucket[rank]++;
        }

        for (char suit : suits) {
            suitBucket[suit - 'a']++;
        }

        for (int count : suitBucket) {
            if (count == 5) {
                return "Flush";
            }
        }

        for (int count : rankBucket) {
            if (count >= 3) {
                return "Three of a Kind";
            }

            if (count == 2) {
                return "Pair";
            }
        }

        return "High Card";
    }
}
