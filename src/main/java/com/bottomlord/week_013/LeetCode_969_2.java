package com.bottomlord.week_013;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_969_2 {
    public List<Integer> pancakeSort(int[] A) {
        int maxIndex = A.length - 1;
        List<Integer> ans = new ArrayList<>();

        while (maxIndex > 0) {
            int maxNumIndex = findMaxNumIndex(A, maxIndex);

            if (maxNumIndex == maxIndex) {
                maxIndex--;
            } else {
                ans.add(maxNumIndex + 1);
                reserve(A, maxNumIndex);
                ans.add(maxIndex + 1);
                reserve(A, maxIndex);
                maxIndex--;
            }
        }

        return ans;
    }

    private int findMaxNumIndex(int[] arr, int maxIndex) {
        int maxNumIndex = 0;
        for (int i = 0; i <= maxIndex; i++) {
            maxNumIndex = arr[maxNumIndex] > arr[i] ? maxNumIndex : i;
        }
        return maxNumIndex;
    }

    private void reserve(int[] arr, int maxIndex) {
        for (int i = 0; i <= maxIndex / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[maxIndex - i];
            arr[maxIndex - i] = tmp;
        }
    }
}