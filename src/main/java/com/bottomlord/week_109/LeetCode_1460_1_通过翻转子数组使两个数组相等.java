package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-15 14:58:49
 */
public class LeetCode_1460_1_通过翻转子数组使两个数组相等 {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] bucket = new int[1001];
        for (int i = 0; i < target.length; i++) {
            bucket[target[i]]++;
            bucket[arr[i]]--;
        }

        for (int num : bucket) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }
}
