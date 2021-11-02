package com.bottomlord.week_121;

/**
 * @author chen yue
 * @date 2021-11-02 08:44:00
 */
public class LeetCode_1826_1_有缺陷的传感器 {
    public int badSensor(int[] sensor1, int[] sensor2) {
        int i = 0, n = sensor1.length;
        while (i < n && sensor1[i] == sensor2[i]) {
            i++;
        }

        if (i >= n - 1) {
            return -1;
        }

        int index = i;
        while (index < n - 1 && sensor1[index] == sensor2[index + 1]) {
            index++;
        }

        int index2 = i;
        while (index2 < n - 1 && sensor2[index2] == sensor1[index2 + 1]) {
            index2++;
        }

        if (index != index2) {
            if (index == n - 1) {
                return 1;
            }

            if (index2 == n - 1) {
                return 2;
            }
        }

        return -1;
    }
}
