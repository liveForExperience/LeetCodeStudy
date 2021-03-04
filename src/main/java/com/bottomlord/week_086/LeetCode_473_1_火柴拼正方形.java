package com.bottomlord.week_086;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/3/4 12:10
 */
public class LeetCode_473_1_火柴拼正方形 {
    public boolean makesquare(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0 || sum < 4) {
            return false;
        }

        int sideLen = sum / 4;

        return backTrack(0, 0, sideLen, map, nums, new HashSet<>());
    }

    private boolean backTrack(int curLen, int sideIndex, int sideLen, Map<Integer, Integer> map, int[] nums, Set<String> memo) {
        if (curLen == sideLen) {
            sideIndex++;
            curLen = 0;
        }

        if (sideIndex == 4 && curLen == 0 && isEmpty(map)) {
            return true;
        }

        if (memo.contains("" + curLen + "-" + snapshot(map))) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 0) {
                continue;
            }

            if (curLen + nums[i] <= sideLen) {
                map.put(nums[i], map.get(nums[i]) - 1);
                boolean result = backTrack(curLen + nums[i], sideIndex, sideLen, map, nums, memo);

                if (result) {
                    return true;
                }

                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }

        memo.add("" + curLen + "-" + snapshot(map));
        return false;
    }

    private boolean isEmpty(Map<Integer, Integer> map) {
        for (Integer key : map.keySet()) {
            if (map.get(key) != 0) {
                return false;
            }
        }

        return true;
    }

    private String snapshot(Map<Integer, Integer> map) {
        return map.keySet().stream().filter(x -> map.get(x) != 0).map(x -> "" + x).collect(Collectors.joining(" "));
    }
}
