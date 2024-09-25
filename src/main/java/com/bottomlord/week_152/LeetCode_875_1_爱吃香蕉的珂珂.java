package com.bottomlord.week_152;

/**
 * @author chen yue
 * @date 2022-06-07 23:43:05
 */
public class LeetCode_875_1_爱吃香蕉的珂珂 {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int pile : piles) {
            r = Math.max(pile, r);
        }

        int ans = 0;
        while (l <= r) {
            int mid = (r - l) / 2 + l;

            int time = getTime(piles, mid);

            if (time <= h) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    private int getTime(int[] piles, int speed) {
        int time = 0;
        for (int pile : piles) {
            time += (pile + speed - 1) / speed;
        }
        return time;
    }
}
