package com.bottomlord.week_102;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/6/24 9:05
 */
public class LeetCode_1260_1_二维网格迁移 {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int row = grid.length, col = grid[0].length, sum = row * col;
        int[] arr = new int[sum];

        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[index++] = grid[i][j];
            }
        }

        int move = k % sum;
        int[] newArr = new int[sum];
        System.arraycopy(arr, sum - move, newArr, 0, move);
        System.arraycopy(arr, 0, newArr, move, sum - move);

        List<List<Integer>> ans = new ArrayList<>();
        index = 0;
        for (int i = 0; i < row; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                list.add(newArr[index++]);
            }
            ans.add(list);
        }
        return ans;
    }
}
