package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/20 8:36
 */
public class LeetCode_1346_3 {
    public boolean checkIfExist(int[] arr) {
        int[] count = new int[2001];
        for (int num : arr) {
            int d = num * 2 + 1000;
            if (d >= 0 && d <= 2000 && count[d] > 0) {
                return true;
            }

            if (num % 2 == 0 && count[num / 2 + 1000] > 0) {
                return true;
            }

            count[num +1000]++;
        }

        return false;
    }
}
