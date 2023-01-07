package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-08 07:03:06
 */
public class LeetCode_2511_1_最多可以摧毁的敌人城堡数目 {
    public int captureForts(int[] forts) {
        int ans = 0, n = forts.length;
        for (int i = 0; i < n; i++) {
            if (forts[i] != 1) {
                continue;
            }

            int index = i;
            for (int j = i + 1; j < n; j++) {
                if (forts[j] == 1) {
                    break;
                }

                if (forts[j] == -1) {
                    index = j;
                    break;
                }
            }

            ans = Math.max(index - i - 1, ans);

            index = i;
            for (int j = i - 1; j >= 0; j--) {
                if (forts[j] == 1) {
                    break;
                }

                if (forts[j] == -1) {
                    index = j;
                    break;
                }
            }

            ans = Math.max(i - index - 1, ans);
        }

        return ans;
    }
}
