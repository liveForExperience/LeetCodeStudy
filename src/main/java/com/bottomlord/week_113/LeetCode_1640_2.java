package com.bottomlord.week_113;

/**
 * @author chen yue
 * @date 2021-09-09 08:56:37
 */
public class LeetCode_1640_2 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Integer[] bucket = new Integer[101];
        for (int i = 0; i < pieces.length; i++) {
            bucket[pieces[i][0]] = i;
        }

        for (int i = 0; i < arr.length;) {
            Integer index = bucket[arr[i]];
            if (index == null) {
                return false;
            }

            int[] piece = pieces[index];
            for (int num : piece) {
                if (arr[i] != num) {
                    return false;
                }

                i++;
            }
        }

        return true;
    }
}
