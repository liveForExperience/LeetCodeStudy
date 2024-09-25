package com.bottomlord.week_097;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/5/18 8:14
 */
public class LeetCode_1442_1_形成两个异或相等数组的三元组数目 {
    public int countTriplets(int[] arr) {
        int[] xor = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            xor[i + 1] = xor[i] ^ arr[i];
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {
                    if (xor[i] == xor[k + 1]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
