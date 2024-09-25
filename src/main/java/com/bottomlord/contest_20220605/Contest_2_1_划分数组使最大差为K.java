package com.bottomlord.contest_20220605;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-06-05 10:28:48
 */
public class Contest_2_1_划分数组使最大差为K {
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int[] operation : operations) {
            int pre = operation[0], cur = operation[1];
            int pi = map.get(pre);

            map.remove(pre);
            map.put(cur, pi);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            nums[entry.getValue()] = entry.getKey();
        }

        return nums;
    }
}
