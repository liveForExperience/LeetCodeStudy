package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-17 08:33:41
 */
public class LeetCode_1491_1_去掉最低工资和最高工资后的平均工资 {
    public double average(int[] salary) {
        double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0;
        for (int num : salary) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            sum += num;
        }

        return (sum - max - min) / (salary.length - 2);
    }
}
