package com.bottomlord.week_194;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-26 14:33:17
 */
public class LeetCode_957_2 {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        int round = n, index = 0;
        Map<Integer, Integer> map = new HashMap<>(), map2 = new HashMap<>();
        int curMask = arr2Mask(pre);
        map.put(curMask, index);
        map2.put(index, curMask);
        index++;

        while (round-- > 0) {
            int[] cur = transfer(pre);
            int mask = arr2Mask(cur);
            if (map.containsKey(mask)) {
                int mod = round % (n - round - map.get(mask));
                return mask2Arr(map2.get(map.get(mask) + mod));
            }

            map.put(mask, index);
            map2.put(index, mask);
            index++;
            pre = cur;
        }

        return pre;
    }

    private int[] transfer(int[] pre) {
        int[] cur = new int[8];
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                cur[i] = 0;
                continue;
            }

            if (pre[i - 1] != pre[i + 1]) {
                cur[i] = 0;
            } else {
                cur[i] = 1;
            }
        }

        return cur;
    }

    private int arr2Mask(int[] arr) {
        int mask = 0;
        for (int i = 0; i < arr.length; i++) {
            mask |= arr[i] << (7 - i);
        }
        return mask;
    }

    private int[] mask2Arr(int n) {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (n & (1 << 7 - i)) == 0 ? 0 : 1;
        }
        return arr;
    }
}
