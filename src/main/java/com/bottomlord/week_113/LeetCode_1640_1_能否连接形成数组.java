package com.bottomlord.week_113;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-09-08 08:44:51
 */
public class LeetCode_1640_1_能否连接形成数组 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }

        for (int i = 0; i < arr.length;) {
            Integer index = map.get(arr[i]);
            if (index == null) {
                return false;
            }

            int[] piece = pieces[index];
            for (int j = 0; j < piece.length;) {
                if (arr[i++] != piece[j++]) {
                    return false;
                }
            }
        }

        return true;
    }
}
