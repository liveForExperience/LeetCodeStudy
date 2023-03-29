package com.bottomlord.week_193;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-03-26 14:33:17
 */
public class LeetCode_957_2 {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int index = 0, cnt = n;
        Map<Integer, Integer> map = new HashMap<>(), indexMap = new HashMap<>();
        int[] pre = cells;

        while (cnt-- > 0) {
            int[] cur = transfer(pre);
            int curMask = arr2Mask(cur);
            if (map.containsKey(curMask)) {
                break;
            }

            map.put(curMask, index);
            indexMap.put(index, curMask);
            pre = cur;
            index++;
        }

        if (cnt == 0) {
            return pre;
        }

        return mask2Arr(indexMap.get(n % index));
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
        for (int i = 0; i < 8; i++) {
            mask |= (arr[i] << (7 - i));
        }

        return mask;
    }

    private int[] mask2Arr(int mask) {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (mask & (1 << (7 - i))) == 0 ? 0 : 1;
        }
        return arr;
    }
}
