package com.bottomlord.week_249;

import com.bottomlord.LeetCodeUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-04-18 17:03:02
 */
public class LeetCode_2007_1_从双倍数组中还原原数组 {

    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 == 1) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            queue.offer(num);
        }

        int n = changed.length / 2, index = 0;
        int[] ans = new int[n];
        while (n-- > 0) {
            int cur = queue.peek();
            if (map.get(cur) == 0) {
                queue.poll();
                n++;
                continue;
            }

            ans[index++] = cur;
            map.put(cur, map.getOrDefault(cur, 0) - 1);
            map.put(cur * 2, map.getOrDefault(cur * 2, 0) - 1);

            if (map.get(cur * 2) < 0) {
                return new int[0];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_2007_1_从双倍数组中还原原数组  t = new LeetCode_2007_1_从双倍数组中还原原数组();
        t.findOriginalArray(LeetCodeUtils.convertToIntArr("[0,0,3]"));
    }
}
