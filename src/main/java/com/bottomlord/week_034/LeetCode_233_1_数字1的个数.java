package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/2/29 19:55
 */
public class LeetCode_233_1_数字1的个数 {
    public int countDigitOne(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i *= 10) {
            int xyzd = n / i, abc = n % i, xyz = xyzd / 10, d = xyzd % 10;

            if (d == 0) {
                ans += xyz * i;
            } else if (d == 1) {
                ans += xyz * i + abc + 1;
            } else {
                ans += xyz * i + i;
            }

            if (xyz == 0) {
                break;
            }
        }

        return ans;
    }
}
