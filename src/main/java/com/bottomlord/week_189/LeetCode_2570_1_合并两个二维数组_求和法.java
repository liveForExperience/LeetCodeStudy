package com.bottomlord.week_189;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-26 19:51:47
 */
public class LeetCode_2570_1_合并两个二维数组_求和法 {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> list = new ArrayList<>();
        int i1 = 0, n1 = nums1.length, i2 = 0, n2 = nums2.length;
        while (i1 < n1 || i2 < n2) {
            if (i1 >= n1) {
                list.add(nums2[i2++]);
                continue;
            }

            if (i2 >= n2) {
                list.add(nums1[i1++]);
                continue;
            }

            int id1 = nums1[i1][0], id2 = nums2[i2][0];
            if (id1 < id2) {
                list.add(nums1[i1++]);
            } else if (id1 > id2) {
                list.add(nums2[i2++]);
            } else {
                list.add(new int[]{id1, nums1[i1][1] + nums2[i2][1]});
                i1++;
                i2++;
            }
        }

        return list.toArray(new int[list.size()][2]);
    }
}
