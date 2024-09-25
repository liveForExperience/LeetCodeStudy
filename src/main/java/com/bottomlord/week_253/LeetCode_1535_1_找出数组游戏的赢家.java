package com.bottomlord.week_253;

/**
 * @author chen yue
 * @date 2024-05-19 19:47:23
 */
public class LeetCode_1535_1_找出数组游戏的赢家 {
    public int getWinner(int[] arr, int k) {
        int max = arr[0], cnt = 0;
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];

            if (num > max) {
                max = num;
                cnt = 1;

                if (cnt == k) {
                    return num;
                }

                continue;
            }

            cnt++;

            if (cnt == k) {
                return max;
            }
        }

        return max;
    }
}
