package com.bottomlord.week_3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/24 16:58
 */
public class LeetCode_893_1_特殊等价字符串组 {
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String str : A) {
            int[] arr = new int[52];
            for (int i = 0; i < str.length(); i++) {
                arr[str.charAt(i) - 'a' + 26 * (i % 2)]++;
            }
            set.add(Arrays.toString(arr));
        }
        return set.size();
    }
}
