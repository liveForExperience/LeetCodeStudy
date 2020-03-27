package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/27 12:54
 */
public class Interview_0503_2 {
    public int reverseBits(int num) {
        int time = 32, cur = 0, pre = 0, ans = 0;

        while (time-- > 0) {
            if ((num & 1) == 0) {
                cur = cur - pre;
                pre = cur;
            }

            ans = Math.max(ans, cur);
            num >>= 1;
            cur++;
        }

        return ans;
    }
}
