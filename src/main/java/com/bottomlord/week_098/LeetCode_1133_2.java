package com.bottomlord.week_098;

/**
 * @author ChenYue
 * @date 2021/5/29 22:17
 */
public class LeetCode_1133_2 {
    public int largestUniqueNumber(int[] A) {
        int[] bucket = new int[1001];
        for (int num : A) {
            bucket[num]++;
        }

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 1) {
                return i;
            }
        }

        return -1;
    }
}
