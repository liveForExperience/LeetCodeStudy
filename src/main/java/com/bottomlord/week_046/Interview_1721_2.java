package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/19 8:31
 */
public class Interview_1721_2 {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }

        int ans = 0, head = 0, tail = height.length - 1;
        int headMax = height[head], tailMax = height[tail];

        while (head < tail) {
            if (headMax < tailMax) {
                ans += headMax - height[head++];
                headMax = Math.max(headMax, height[head]);
            } else {
                ans += tailMax - height[tail--];
                tailMax = Math.max(tailMax, height[tail]);
            }
        }

        return ans;
    }
}