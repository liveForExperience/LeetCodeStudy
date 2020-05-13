package com.bottomlord.week_045;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/5/13 16:42
 */
public class Interview_1718_2 {
    public int[] shortestSeq(int[] big, int[] small) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : small) {
            map.put(num, 0);
        }
        int count = 0, start = 0, size = small.length, len = big.length;
        int[] ans = new int[]{-1, len};

        for (int i = 0; i < len; i++) {
            int num = big[i];
            if (map.containsKey(num)) {
                if (map.get(num) == 0) {
                    count++;
                }

                map.put(num, map.get(num) + 1);
            }

            if (count == size) {
                while (start <= i) {
                    int startNum = big[start];
                    if (map.containsKey(startNum)) {
                        map.put(startNum, map.get(startNum) - 1);

                        if (map.get(startNum) == 0) {
                            if (i - start < ans[1] - ans[0]) {
                                ans[0] = start;
                                ans[1] = i;
                            }

                            start++;
                            break;
                        }
                    }
                    start++;
                }
                count--;
            }
        }

        return ans[0] == -1 ? new int[0] : ans;
    }
}