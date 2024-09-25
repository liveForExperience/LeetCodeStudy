package com.bottomlord.week_042;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2020/4/24 8:33
 */
public class Interview_1621_1_交换和 {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = Arrays.stream(array1).sum(),
            sum2 = Arrays.stream(array2).sum(),
            diff = sum1 - sum2;

        if ((diff & 1) == 1) {
            return new int[0];
        }

        diff /= 2;
        Set<Integer> set = Arrays.stream(array2).boxed().collect(Collectors.toSet());
        for (int num : array1) {
            if (set.contains(num - diff)) {
                return new int[]{num, num - diff};
            }
        }

        return new int[0];
    }
}
