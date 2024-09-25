package com.bottomlord.week_060;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/24 9:01
 */
public class LeetCode_229_1_求众数II {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, count1 = 0,
            candidate2 = 0, count2 = 0;

        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
                continue;
            }

            if (candidate2 == num) {
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

        List<Integer> ans = new ArrayList<>();
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
            } else if (candidate2 == num) {
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
