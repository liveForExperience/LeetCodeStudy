package com.bottomlord.week_073;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/12/4 18:54
 */
public class LeetCode_659_2 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>(), endMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (countMap.get(num) == 0) {
                continue;
            }

            int preEndCount = endMap.getOrDefault(num - 1, 0);
            if (preEndCount > 0) {
                endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                endMap.put(num - 1, preEndCount - 1);
                countMap.put(num, countMap.get(num) - 1);
            } else {
                int secondCount = countMap.getOrDefault(num + 1, 0),
                    thirdCount = countMap.getOrDefault(num + 2, 0);

                if (secondCount > 0 && thirdCount > 0) {
                    countMap.put(num, countMap.get(num) - 1);
                    countMap.put(num + 1, secondCount - 1);
                    countMap.put(num + 2, thirdCount - 1);
                    endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
