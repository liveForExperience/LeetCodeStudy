package com.bottomlord.week_067;

/**
 * @author ChenYue
 * @date 2020/10/19 8:28
 */
public class LeetCode_306_1_累加数 {
    public boolean isAdditiveNumber(String num) {
        return backTrack(num, 0, 0, 0, 0);
    }

    private boolean backTrack(String num, int index, int pre, int sum, int count) {
        if (index == num.length()) {
            return count > 2;
        }

        for (int i = index; i < num.length(); i++) {
            int cur = getNum(num, i, num.length());

            if (cur == -1) {
                return false;
            }

            if (count > 2 && cur != sum) {
                return false;
            }

            if (backTrack(num, index + 1, cur, pre + cur, count + 1)) {
                return true;
            }
        }

        return false;
    }

    private int getNum(String num, int l, int r) {
        if (l < r && num.charAt(l) == '0') {
            return -1;
        }

        int ans = 0;
        for (int i = 0; i < r - l; i++) {
            ans += 10 * i + num.charAt(l + i);
        }
        return ans;
    }
}
