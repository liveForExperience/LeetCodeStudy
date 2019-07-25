package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/25 22:39
 */
public class LeetCode_496_1_下一个更大元素I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        int index = 0;

        for (int x : nums1) {
            boolean equal = false;
            boolean find = false;
            for (int y : nums2) {
                if (x == y) {
                    equal = true;
                    continue;
                }

                if (equal && y > x) {
                    find = true;
                    ans[index++] = y;
                    break;
                }
            }

            if (!find) {
                ans[index++] = -1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_496_1_下一个更大元素I test = new LeetCode_496_1_下一个更大元素I();
        test.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
    }
}
