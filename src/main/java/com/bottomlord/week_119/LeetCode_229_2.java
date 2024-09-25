package com.bottomlord.week_119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-22 08:44:50
 */
public class LeetCode_229_2 {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, count1 = 0,
                candidate2 = 0, count2 = 0;

        for (int num : nums) {

            if (num == candidate1) {
                count1++;
                continue;
            }

            if (num == candidate2) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }

            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        if (candidate1 == candidate2) {
            return Collections.singletonList(candidate1);
        }

        List<Integer> ans = new ArrayList<>();
        count1 = count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            }

            if (num == candidate2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3) {
            ans.add(candidate1);
        }

        if (count2 > nums.length / 3) {
            ans.add(candidate2);
        }

        return ans;
    }
}
