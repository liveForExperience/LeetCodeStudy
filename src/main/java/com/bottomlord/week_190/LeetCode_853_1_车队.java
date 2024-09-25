package com.bottomlord.week_190;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author chen yue
 * @date 2023-03-01 11:24:40
 */
public class LeetCode_853_1_车队 {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] arr = new double[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = position[i];
            arr[i][1] = (target - position[i]) / (double) speed[i];
        }

        Arrays.sort(arr, (x, y) -> (int) (x[0] - y[0]));

        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            double time = arr[i][1];

            while (!stack.isEmpty() && time >= stack.peek()) {
                stack.pop();
            }

            stack.push(time);
        }

        return stack.size();
    }
}
