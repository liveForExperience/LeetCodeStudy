package com.bottomlord.week_069;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/11/2 18:20
 */
public class LeetCode_321_1_拼接最大数 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length, len2 = nums2.length;
        int[] ans = new int[k];
        if (len1 + len2 < k) {
            return ans;
        }

        for (int i = Math.max(k - len2, 0); i <= Math.min(len1, k); i++) {
            List<Integer> l1 = getLargestNum(nums1, i),
                          l2 = getLargestNum(nums2, k - i);

            int[] tmp = merge(l1, l2);

            if (isLarger(tmp, ans)) {
                ans = tmp;
            }
        }

        return ans;
    }

    private List<Integer> getLargestNum(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        int removeCount = nums.length - k;
        if (k == 0) {
            return ans;
        }

        for (int i = 0; i < nums.length; i++) {
            while (ans.size() > 0 && nums[i] > ans.get(ans.size() - 1) && removeCount > 0) {
                ans.remove(ans.size() - 1);
                removeCount--;
            }

            if (ans.size() < k) {
                ans.add(nums[i]);
            } else {
                removeCount--;
            }
        }

        return ans;
    }

    private int[] merge(List<Integer> a, List<Integer> b) {
        int[] ans = new int[a.size() + b.size()];
        int ia = 0, ib = 0;
        StringBuilder sba = new StringBuilder(), sbb = new StringBuilder();
        a.forEach(sba::append);
        b.forEach(sbb::append);

        for (int i = 0; i < ans.length; i++) {
            if (ia >= a.size()) {
                ans[i] = b.get(ib++);
            } else if (ib >= b.size()) {
                ans[i] = a.get(ia++);
            } else {
                if (sba.substring(ia).compareTo(sbb.substring(ib)) >= 0) {
                    ans[i] = a.get(ia++);
                } else {
                    ans[i] = b.get(ib++);
                }
            }
        }

        return ans;
    }

    private boolean isLarger(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return true;
            } else if (a[i] < b[i]) {
                return false;
            }
        }

        return false;
    }
}
