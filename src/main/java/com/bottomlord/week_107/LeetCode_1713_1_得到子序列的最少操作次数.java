package com.bottomlord.week_107;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/7/26 8:31
 */
public class LeetCode_1713_1_得到子序列的最少操作次数 {
    public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                int index = map.get(num);
                int i = binarySearch(ans, index);
                if (i == ans.size()) {
                    ans.add(index);
                } else {
                    ans.set(i, index);
                }
            }
        }

        return target.length - ans.size();
    }

    private int binarySearch(List<Integer> ans, int num) {
        int size = ans.size();
        if (size == 0 || num > ans.get(size - 1)) {
            return size;
        }

        int head = 0, tail = size - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (ans.get(mid) < num) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
