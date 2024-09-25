package com.bottomlord.week_092;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_220_2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Map<Long, Long> map = new HashMap<>();
        int width = t + 1;
        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], width);

            if (map.containsKey(id)) {
                return true;
            }

            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) <= k) {
                return true;
            }

            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) <= k) {
                return true;
            }

            map.put(id, (long)nums[i]);

            if (i >= k) {
                map.remove(getId(nums[i - k], width));
            }
        }

        return false;
    }

    private long getId(int num, int width) {
        return num >= 0 ? num / width : (num + 1) / width - 1;
    }
}
