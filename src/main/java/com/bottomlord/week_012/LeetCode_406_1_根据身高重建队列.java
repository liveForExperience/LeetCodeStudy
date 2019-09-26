package com.bottomlord.week_012;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_406_1_根据身高重建队列 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        List<int[]> list = new LinkedList<>();
        for (int[] arr : people) {
            list.add(arr[1], arr);
        }

        return list.toArray(new int[0][0]);
    }
}
