package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/28 16:56
 */
public class Interview_0504_1_下一个数 {
    public int[] findClosedNumbers(int num) {
        int count = count1(num);
        int[] ans = new int[]{-1, -1};
        int large =  num + 1;
        while (large >= 1) {
            if (count == count1(large)) {
                ans[0] = large;
                break;
            }

            large++;
        }

        int small = num - 1;
        while (small >= 1) {
            if (count == count1(small)) {
                ans[1] = small;
                break;
            }

            small--;
        }

        return ans;
    }

    private int count1(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>= 1;
        }

        return count;
    }
}
