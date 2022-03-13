package com.bottomlord.week_139;

/**
 * @author chen yue
 * @date 2022-03-13 12:40:46
 */
public class LeetCode_393_1_UTF8编码验证 {
    public boolean validUtf8(int[] data) {
        int count = 0, mask1 = (1 << 7), mask2 = (1 << 6);
        for (int num : data) {
            if (count == 0) {
                if ((num & mask1) == 0) {
                    continue;
                }

                while ((num & mask1) != 0) {
                    count++;
                    num <<= 1;
                }

                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (!((num & mask1) != 0 || (num & mask2) == 0)) {
                    return false;
                }
            }

            count--;
        }

        return count == 0;
    }
}
