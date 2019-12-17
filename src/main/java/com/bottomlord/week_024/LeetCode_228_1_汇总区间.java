package com.bottomlord.week_024;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_228_1_汇总区间 {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int start = nums[0], pre = start;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 != pre) {
                if (start == pre) {
                    ans.add("" + start);
                } else {
                    ans.add(start + "->" + pre);
                }

                start = nums[i];
            }
            pre = nums[i];
        }

        if (start == pre) {
            ans.add("" + start);
        } else {
            ans.add(start + "->" + pre);
        }

        return ans;
    }
}
