package com.bottomlord.week_009;

public class LeetCode_914_1_卡牌分组 {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length < 2) {
            return false;
        }

        int[] bucket = new int[10000];
        for (int num: deck) {
            bucket[num]++;
        }

        int first = -1, commonDivisor = Integer.MAX_VALUE;
        for (int num: bucket) {
            if (num > 0) {
                if (first == -1) {
                    first = num;
                }

                int tmp = commondivisior(first, num);
                commonDivisor = tmp == 1 ? commonDivisor : Math.min(tmp, commonDivisor);
            }
        }

        for (int num : bucket) {
            if (num % commonDivisor != 0) {
                return false;
            }
        }

        return true;
    }

    private int commondivisior(int a, int b) {
        if (a < b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }

        return a % b == 0 ? b : commondivisior(b, a % b);
    }
}
