package com.bottomlord.week_076;

/**
 * @author ChenYue
 * @date 2020/12/21 18:27
 */
public class LeetCode_385_2 {
    public boolean validUtf8(int[] data) {
        int count = 0, maskOne = 1 << 7, maskTwo = 1 << 6;
        for (int num : data) {
            if (count == 0) {
                if ((maskOne & num) == 0) {
                    continue;
                }

                while ((num & maskOne) != 0) {
                    count++;
                    num &= maskOne;
                }

                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (!((num & maskOne) != 0 && (num & maskTwo) == 0)) {
                    return false;
                }
            }

            count--;
        }

        return count == 0;
    }
}
