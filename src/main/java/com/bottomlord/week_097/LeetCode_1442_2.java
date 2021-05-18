package com.bottomlord.week_097;

/**
 * @author ChenYue
 * @date 2021/5/18 9:23
 */
public class LeetCode_1442_2 {
    public int countTriplets(int[] arr) {
        int len = arr.length;
        int[] xor = new int[len + 1];
        for (int i = 0; i < arr.length; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }

        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int k = i + 1; k < len; k++) {
                if (xor[i] == xor[k + 1]) {
                    count += k - i;
                }
            }
        }

        return count;
    }
}
