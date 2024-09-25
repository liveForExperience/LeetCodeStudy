package com.bottomlord.week_043;

/**
 * @author ChenYue
 * @date 2020/4/29 8:49
 */
public class Interview_1706_1_2出现的次数 {
    public int numberOf2sInRange(int n) {
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            int num = i;
            while (num > 0) {
                ans += num % 10 == 2 ? 1 : 0;
                num /= 10;
            }
        }
        return ans;
    }
}
