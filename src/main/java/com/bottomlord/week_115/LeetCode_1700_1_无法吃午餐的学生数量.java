package com.bottomlord.week_115;

/**
 * @author chen yue
 * @date 2021-09-23 22:12:00
 */
public class LeetCode_1700_1_无法吃午餐的学生数量 {
    public int countStudents(int[] students, int[] sandwiches) {
        int one = 0, zero = 0;
        for (int student : students) {
            if (student == 0) {
                zero++;
            } else {
                one++;
            }
        }

        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (zero == 0) {
                    return one;
                }

                zero--;
            } else {
                if (one == 0) {
                    return zero;
                }

                one--;
            }
        }

        return 0;
    }
}
