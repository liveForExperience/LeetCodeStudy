package com.bottomlord.week_038;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/3/28 18:14
 */
public class Interview_0504_2 {
    public int[] findClosedNumbers(int num) {
        String str = Integer.toBinaryString(num);
        int len = str.length(), count = 0, index = -1;
        char[] small = str.toCharArray();
        int[] ans = new int[]{-1, -1};

        for (int i = len - 1; i > 0; i--) {
            if (small[i] == '0' && small[i - 1] == '1') {
                 small[i] = '1';
                 small[i - 1] = '0';
                 index = i + 1;
                 break;
            }

            if (small[i] == '1') {
                count++;
            }
        }

        if (index != -1) {
            for (; index < len; index++) {
                if (count-- > 0) {
                    small[index] = '1';
                } else {
                    small[index] = '0';
                }
            }
            ans[1] = Integer.parseInt(new String(small), 2);
            index = -1;
        }

        char[] large = ("0" + str).toCharArray();
        count = 0;
        for (int i = len; i > 0; i--) {
            if (large[i] == '1' && large[i - 1] == '0') {
                large[i] = '0';
                large[i - 1] = '1';
                index = i + 1;
                break;
            }

            if (large[i] == '1') {
                count++;
            }
        }

        if (index != -1) {
            for (int i = len; i >= index; i--) {
                if (count-- > 0) {
                    large[i] = '1';
                } else {
                    large[i] = '0';
                }
            }

            long largeNum = Long.parseLong(new String(large), 2);

            if (largeNum <= Integer.MAX_VALUE) {
                ans[0] = (int) largeNum;
            }
        }

        return ans;
    }
}