package com.bottomlord.week_097;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/5/19 8:17
 */
public class LeetCode_1738_1_找出第K大的异或坐标值 {
    public int kthLargestValue(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int[][] xorMatrix = new int[row + 1][col + 1];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                xorMatrix[i][j + 1] = xorMatrix[i][j] ^ matrix[i][j];
            }
        }

        Arrays.stream(xorMatrix[0]).forEach(queue::offer);

        for (int i = 1; i <= col; i++) {
            for (int j = 1; j < row; j++) {
                xorMatrix[j][i] ^= xorMatrix[j - 1][i];
                queue.offer(xorMatrix[j][i]);
            }
        }

        for (int i = 0; i < row; i++) {
            System.out.println(Arrays.toString(xorMatrix[i]));
        }

        int index = 0, ans = -1;
        while (index != k) {
            ans = queue.poll();
            index++;
        }

        return ans;
    }
}
