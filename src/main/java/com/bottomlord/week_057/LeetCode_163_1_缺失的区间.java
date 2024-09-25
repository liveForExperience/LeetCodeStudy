package com.bottomlord.week_057;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/3 8:23
 */
public class LeetCode_163_1_缺失的区间 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int index = 0, len = nums.length;
        for (int i = lower; i <= upper && index < len;) {
            if (i == nums[index]) {
                index++;
                i++;
            } else {
                int start = i;
                StringBuilder sb = new StringBuilder(start);
                i++;
                while (i != nums[index] && i <= upper) {
                    i++;
                }
                int end = i - 1;
                if (start != end) {
                    sb.append("->").append(end);
                }
                ans.add(sb.toString());
            }
        }

        return ans;
    }
}
