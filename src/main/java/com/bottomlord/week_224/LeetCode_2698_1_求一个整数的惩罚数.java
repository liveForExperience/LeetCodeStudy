package com.bottomlord.week_224;

/**
 * @author chen yue
 * @date 2023-10-25 09:51:51
 */
public class LeetCode_2698_1_求一个整数的惩罚数 {
    public int punishmentNumber(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int pow = i * i;
            if (backTrack(0, Integer.toString(pow), 0, i)) {
                sum += pow;
            }
        }

        return sum;
    }

    private boolean backTrack(int index, String s, int sum, int target) {
        if (index == s.length()) {
            return sum == target;
        }

        for (int i = index; i < s.length(); i++) {
            int cur = Integer.parseInt(s.substring(index, i + 1));

            if (backTrack(i + 1, s, sum + cur, target)) {
                return true;
            }
        }

        return false;
    }
}
