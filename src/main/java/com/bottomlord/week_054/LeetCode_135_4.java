package com.bottomlord.week_054;

/**
 * @author ChenYue
 * @date 2020/7/16 8:41
 */
public class LeetCode_135_4 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        if (len <= 1) {
            return len;
        }

        int pre = 0, cur = 0, up = 0, down = 0, ans = 0;
        for (int i = 1; i < len; i++) {
            cur = Integer.compare(ratings[i], ratings[i - 1]);
            if (cur == 0 && pre == 1 || cur >= 0 && pre == -1) {
                ans += count(up) + count(down) + Math.max(down, up);
                up = 0;
                down = 0;
            }

            if (cur == 1) {
                up++;
            } else if (cur == -1) {
                down++;
            } else {
                ans++;
            }

            pre = cur;
        }

        ans += count(up) + count(down) + Math.max(down, up) + 1;

        return ans;
    }

    private int count(int n) {
        return (n + 1) * n / 2;
    }
}