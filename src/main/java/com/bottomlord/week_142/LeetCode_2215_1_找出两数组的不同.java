package com.bottomlord.week_142;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-03-31 21:33:08
 */
public class LeetCode_2215_1_找出两数组的不同 {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }

        for (Integer num : nums2) {
            set2.add(num);
        }

        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> ans1 = new HashSet<>(), ans2 = new HashSet<>();

        for (int num : nums1) {
            if (!set2.contains(num)) {
                ans1.add(num);
            }
        }

        for (int num : nums2) {
            if (!set1.contains(num)) {
                ans2.add(num);
            }
        }

        ans.add(new ArrayList<>(ans1));
        ans.add(new ArrayList<>(ans2));
        return ans;
    }
}
