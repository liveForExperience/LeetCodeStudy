package com.bottomlord.week_030;

/**
 * @author ThinkPad
 * @date 2020/1/31 19:58
 */
public class LeetCode_846_2 {
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) {
            return false;
        }

        int[] arr = new int[W];
        for (int num : hand) {
            arr[num % W]++;
        }

        for (int i = 0; i < W - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                return false;
            }
        }

        return true;
    }
}